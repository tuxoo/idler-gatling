package com.idler.util

import io.gatling.core.session._
import io.gatling.core.Predef._

import java.util.concurrent.atomic.AtomicInteger

/**
 * Class is intended to check if every session is started or stopped.
 * This allows us to stop test when all orders are checked and not to wait until test time is over.
 * Apply counters when scenario uses doWhile/asLongAs loops
 * @param newSession session start mark
 * @param oldSession session stop mark
 */

case class SessionCounter(var newSession: AtomicInteger = new AtomicInteger(0), var oldSession: AtomicInteger = new AtomicInteger(0)) {

  private[idler] def collectNewSession: Expression[Session] = session => {
    newSession.incrementAndGet()
    session
  }

  private[idler] def collectOldSession: Expression[Session] = session => {
    oldSession.incrementAndGet()
    session
  }
}
