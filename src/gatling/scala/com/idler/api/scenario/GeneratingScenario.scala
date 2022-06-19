package com.idler.api.scenario

import com.idler.api.request.InsertingUsers.insertUsers
import com.idler.config.Config.openUsersCount
import com.idler.util.Utils.executeUserGeneration
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import java.util.concurrent.atomic.AtomicInteger

class GeneratingScenario {

  private val userCounter = new AtomicInteger(openUsersCount)

  def GeneratingScn(scn: Int): ScenarioBuilder =
    scn match {
      case 1 =>
        scenario("InjectUsers")
          .repeat(1) {
            executeUserGeneration(userCounter, insertUsers())
          }
    }
}
