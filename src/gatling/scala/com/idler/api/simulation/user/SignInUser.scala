package com.idler.api.simulation.user

import com.idler.api.scenario.UserScenarios
import com.idler.util.HttpUtils.{httpProtocol, injectionSteps}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class SignInUser extends Simulation {

  val scn = new UserScenarios()
  setUp(
    scn.userScn(1).inject(injectionSteps).protocols(httpProtocol)
  )
}
