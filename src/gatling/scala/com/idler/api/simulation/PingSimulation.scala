package com.idler.api.simulation

import com.idler.api.request.Ping.ping
import com.idler.config.Config.baseUrl
import com.idler.util.HttpUtils.{httpProtocol, openInjectionSteps}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._
import scala.language.postfixOps

class PingSimulation extends Simulation {
  val snc: ScenarioBuilder = scenario("Ping").exec(ping)

  setUp(snc.inject(openInjectionSteps).protocols(httpProtocol))
}
