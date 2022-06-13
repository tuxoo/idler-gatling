package com.idler.util

import io.gatling.core.Predef._
import com.idler.config.Config.{RPS, baseUrl, ramp, scnDuration}
import io.gatling.core.controller.inject.closed.ClosedInjectionStep
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.collection.Seq

object HttpUtils {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .contentTypeHeader("application/json")
    .disableWarmUp

  val injectionSteps: Seq[ClosedInjectionStep] = Seq(
//    rampConcurrentUsers(0) to RPS.toInt during ramp,
    constantConcurrentUsers(RPS.toInt) during scnDuration
  )
}
