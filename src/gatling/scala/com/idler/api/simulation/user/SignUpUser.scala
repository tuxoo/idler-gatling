package com.idler.api.simulation.user

import com.idler.api.scenario.UserScenarios
import com.idler.config.Config.{dao, maxScnDuration}
import com.idler.util.HttpUtils.{httpProtocol, openInjectionSteps}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class SignUpUser extends Simulation {

  dao.prepareDb()

  val scn = new UserScenarios()
  setUp(
    scn.userScn(1).inject(openInjectionSteps).protocols(httpProtocol)
  ).maxDuration(maxScnDuration)
}
