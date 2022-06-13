package com.idler.api.scenario

import com.idler.api.request.GeneratingUsers.getUsers
import com.idler.config.Config.usersCount
import com.idler.util.Utils.executeUserGeneration
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import java.util.concurrent.atomic.AtomicInteger

class GeneratingScenario {

  private val userCounter = new AtomicInteger(usersCount)

  def GeneratingScn(scn: Int): ScenarioBuilder =
    scn match {
      case 1 =>
        scenario("GeneratingUsers")
          .repeat(1) {
            executeUserGeneration(userCounter, getUsers)
          }
    }
}
