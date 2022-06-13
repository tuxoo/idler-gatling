package com.idler.api.simulation.generation

import com.idler.api.scenario.GeneratingScenario
import com.idler.util.HttpUtils.injectionSteps
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class GenerateUsers extends Simulation {

  val scn = new GeneratingScenario()

  val protocolBuilder: HttpProtocolBuilder = http
    .contentTypeHeader("application/json")
    .disableWarmUp

  setUp(
    scn.GeneratingScn(1).inject(injectionSteps).protocols(protocolBuilder)
  )
}
