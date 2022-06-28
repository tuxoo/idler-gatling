package com.idler.api.simulation.user

import com.idler.api.scenario.UserScenarios
import com.idler.config.Config.maxScnDuration
import com.idler.util.HttpUtils.{closedInjectionSteps, httpProtocol, openInjectionSteps}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class SignInUser extends Simulation {

  val scn = new UserScenarios()
  setUp(
    scn.userScn(3).inject(closedInjectionSteps).protocols(httpProtocol)
  ).maxDuration(maxScnDuration)
}
