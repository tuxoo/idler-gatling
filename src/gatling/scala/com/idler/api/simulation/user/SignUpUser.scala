package com.idler.api.simulation.user

import com.idler.api.scenario.UserScenarios
import com.idler.util.HttpUtils.{closedInjectionSteps, httpProtocol, openInjectionSteps}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class SignUpUser extends Simulation {

  val scn = new UserScenarios()
  setUp(
    scn.userScn(1).inject(openInjectionSteps).protocols(httpProtocol)
//      .andThen(scn.userScn(3).inject(closedInjectionSteps).protocols(httpProtocol))
  )
}
