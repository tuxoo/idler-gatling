package com.idler.api.request

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.idler.config.Config.{dao, genericPasswordHash, jsonUsersFile}
import com.idler.util.Utils.{exitFromTest, sessionCounter}
import com.idler.util.postgres.User
import io.gatling.core.Predef._
import io.gatling.core.session._
import io.gatling.core.structure.ChainBuilder

import java.io.{BufferedWriter, File, FileWriter}
import java.time.LocalDateTime
import java.util.UUID
import scala.collection.mutable
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.language.postfixOps
import scala.util.{Random, Success}

object InsertingUsers {

  private val random = new Random()
  val objectMapper = new ObjectMapper()
  objectMapper.registerModule(DefaultScalaModule)
  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  objectMapper.registerModule(new JavaTimeModule());
  objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

  private val users = mutable.Set.empty[User]

  private[idler] def insertUsers(): ChainBuilder =
    exec(sessionCounter.collectNewSession)
      .exec(generator)
      .exec(sessionCounter.collectOldSession)
      .doIf(session => session("counter").as[Int] == 1) {
        exec(writeData)
      }

  private[idler] def getUserId(email: String): String = {
    val user = dao.getUserByEmail(email)

    user.onComplete {
      case Success(value) => value
    }
    Await.result(user, 3 seconds).toString
  }

  private[idler] val writeData: ChainBuilder =
    doWhile(sessionCounter.oldSession.get() < sessionCounter.newSession.get()) { // all sessions must be done, else waiting
      pace(1 seconds)
        .exec(pause(1 seconds))
    }
      .exec(writeUsers)
      .exec(exitFromTest)

  private def generator: Expression[Session] = session => {

    val testNumber = System.nanoTime() / random.nextInt(1000000)

    val user = User(
      id = UUID.randomUUID(),
      name = String.format(s"User${testNumber}"),
      loginEmail = String.format(s"User${testNumber}@idler.com"),
      passwordHash = genericPasswordHash,
      registeredAt = LocalDateTime.now(),
      visitedAt = LocalDateTime.now(),
      role = "USER",
      isEnabled = true,
    )

    def saveUser = dao.saveUser(user)

    users.add(user)

    Await.ready(saveUser, Duration.Inf)
    session
  }

  private def writeUsers: Expression[Session] = session => {
    val file = new File(jsonUsersFile)
    if (file.exists()) file.delete()
    val usersWriter: BufferedWriter = new BufferedWriter(new FileWriter(jsonUsersFile, true))

    val str = users.map { u => objectMapper.writeValueAsString(u) }.mkString("[", ",\n", "]")
    usersWriter.append(str)
    usersWriter.close()
    session
  }
}
