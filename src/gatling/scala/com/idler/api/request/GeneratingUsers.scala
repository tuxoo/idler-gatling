package com.idler.api.request

import com.idler.config.Config.{dao, genericPasswordHash}
import com.idler.util.Utils.{exitFromTest, sessionCounter}
import com.idler.util.postgres.User
import io.gatling.core.Predef._
import io.gatling.core.session._
import io.gatling.core.structure.ChainBuilder

import java.time.LocalDateTime
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationInt}
import scala.language.postfixOps
import scala.util.Random

object GeneratingUsers {

  private[idler] def getUsers: ChainBuilder =
    exec(sessionCounter.collectNewSession)
      .exec(generator)
      .exec(sessionCounter.collectOldSession)
      .doIf(session => session("counter").as[Int] == 1) {
        exec(writeData)
      }


  private[idler] val writeData: ChainBuilder =
    doWhile(sessionCounter.oldSession.get() < sessionCounter.newSession.get()) { // all sessions must be done, else waiting
      pace(1 seconds)
        .exec(pause(1 seconds))
    }
      .exec(exitFromTest)

  private def generator: Expression[Session] = session => {

    val random = new Random()
    val testNumber = System.nanoTime() / random.nextInt(1000000)

    val user = User(
      name = String.format(s"User${testNumber}"),
      email = String.format(s"User${testNumber}@idler.com"),
      passwordHash = genericPasswordHash,
      registeredAt = LocalDateTime.now(),
      visitedAt = LocalDateTime.now(),
      isConfirmed = true,
    )

    def saveUser = dao.saveUser(user)

    Await.ready(saveUser, Duration.Inf)
    session
  }
}
