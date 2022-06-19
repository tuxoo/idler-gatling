package com.idler.api.simulation.user

import com.idler.api.scenario.UserScenarios
import com.idler.util.HttpUtils.{httpProtocol, openInjectionSteps}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class SignInUser extends Simulation {

  val scn = new UserScenarios()
  setUp(
    scn.userScn(3).inject(openInjectionSteps).protocols(httpProtocol)
  )
}
