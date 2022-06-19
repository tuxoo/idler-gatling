package com.idler.util

import com.idler.config.Config._
import io.gatling.core.Predef._
import io.gatling.core.controller.inject.closed.ClosedInjectionStep
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.collection.Seq

object HttpUtils {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .contentTypeHeader("application/json")
    .disableWarmUp

  val openInjectionSteps: Seq[OpenInjectionStep] = Seq(
//    nothingFor(10),
    rampUsersPerSec(0).to(openUsersCount).during(ramp).randomized,
    constantUsersPerSec(openUsersCount).during(openScnDuration),
    //    stressPeakUsers(1000).during(20)
  )

  val closedInjectionSteps: Seq[ClosedInjectionStep] = Seq(
    rampConcurrentUsers(0) to RPS.toInt during ramp,
    constantConcurrentUsers(RPS.toInt) during openScnDuration
  )
}
