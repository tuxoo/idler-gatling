package com.idler.util

import com.idler.config.Config._
import io.gatling.core.Predef._
import io.gatling.core.controller.inject.closed.ClosedInjectionStep
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.collection.Seq

object HttpUtils {
    val sessionHeaders: Map[String, String] = Map("Authorization" -> "Bearer ${token}")
//  val sessionHeaders: Map[String, String] = Map("Authorization" -> "${token}")

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .contentTypeHeader("application/json")
    .disableWarmUp

  val openInjectionSteps: Seq[OpenInjectionStep] = Seq(
    nothingFor(5),
    rampUsersPerSec(0).to(openUsersCount).during(openScnDuration).randomized,
    stressPeakUsers(openUsersCount).during(stressScnDuration),
    nothingFor(10),
    rampUsersPerSec(0).to(openUsersCount).during(openScnDuration).randomized,
    stressPeakUsers(openUsersCount).during(stressScnDuration),
    nothingFor(5),
    rampUsersPerSec(0).to(openUsersCount).during(openScnDuration).randomized,
  )

  val closedInjectionSteps: Seq[ClosedInjectionStep] = Seq(
    rampConcurrentUsers(0) to closedUsersCount during closedScnDuration / 2,
    constantConcurrentUsers(closedUsersCount) during (closedScnDuration / 2),
  )
}
