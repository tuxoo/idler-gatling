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

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .contentTypeHeader("application/json")
    .disableWarmUp
    .maxConnectionsPerHost(1)
    .shareConnections

  // usersPerSec = 30
  // times = 90
  // levelLasting = 5
  // rampsLasting = 5
  val openInjectionSteps: Seq[OpenInjectionStep] = Seq(
    incrementUsersPerSec(usersPerSec)
      .times(times)
      .eachLevelLasting(levelLasting)
      .separatedByRampsLasting(rampsLasting)
  )

  // usersPerSec = 30
  // times = 90
  // levelLasting = 5
  // rampsLasting = 5
  val closedInjectionSteps: Seq[ClosedInjectionStep] = Seq(
    incrementConcurrentUsers(usersPerSec)
      .times(times)
      .eachLevelLasting(levelLasting)
      .separatedByRampsLasting(rampsLasting)
  )
}
