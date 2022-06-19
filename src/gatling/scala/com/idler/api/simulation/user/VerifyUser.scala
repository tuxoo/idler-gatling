package com.idler.api.simulation.user

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import com.idler.api.scenario.UserScenarios
import com.idler.util.HttpUtils.{httpProtocol, openInjectionSteps}

class VerifyUser extends Simulation {

  val scn = new UserScenarios()
  setUp(
    scn.userScn(2).inject(openInjectionSteps).protocols(httpProtocol)
  )
}
