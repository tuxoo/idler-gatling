package com.idler.api.simulation

import com.idler.api.request.PingRequest.ping
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._
import scala.language.postfixOps

class PingTest extends Simulation {
  val httpConfig: HttpProtocolBuilder = http.baseUrl("http://localhost:8080")
  val snc: ScenarioBuilder = scenario("Ping").exec(ping)

  setUp(snc.inject(constantUsersPerSec(12) during (5 seconds)).protocols(httpConfig))
}
