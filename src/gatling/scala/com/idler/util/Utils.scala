package com.idler.util

import io.gatling.core.Predef._
import io.gatling.core.session.{Expression, Session}
import io.gatling.core.structure.ChainBuilder

import java.util.concurrent.atomic.AtomicInteger

object Utils {

  private[idler] val sessionCounter = SessionCounter()

  private[idler] def executeUserGeneration(counter: AtomicInteger, chainBuilder: ChainBuilder): ChainBuilder = {
    exec(session => session.set("counter", counter.getAndDecrement()))
      .doIf(session => session("counter").as[Int] > 0) {
        chainBuilder
      }
  }

  private[idler] def exitFromTest: Expression[Session] = session => {
    if (sessionCounter.oldSession.get() == sessionCounter.newSession.get()) {
      println("\u001B[31m[GATLING INFO] All orders are checked...\u001B[37m")
      System.exit(0)
    }
    session
  }

  private[idler] def returnResponseBodyOnError(request: String): Expression[Session] = session => {
    println(s"\u001B[31m[GATLING ERROR] [$request] => ${session("responseBody").as[String]}\u001B[37m")
    session.markAsFailed
  }
}
