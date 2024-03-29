package com.idler.api.request.user

import com.idler.api.request.user.GetUserByEmail.getUserByEmail
import com.idler.config.Config.scenarioType
import com.idler.util.HttpUtils.sessionHeaders
import com.idler.util.Utils.{returnResponseBodyOnError, sessionCounter}
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object GetUserProfile {
  private[idler] val getUserProfile: ChainBuilder =
    doIf(scenarioType == "one_request") {
      exec(sessionCounter.collectNewSession)
    }
      .exec(
        http("GetUserProfile")
          .get("/api/v1/user/profile/")
          .headers(sessionHeaders)
          .check(status.is(200))
      )
      .doIfOrElse(session => session.status.name == "KO") {
        exec(returnResponseBodyOnError("Gets user profile failed"))
          .exitHere
      } {
        doIf(scenarioType == "chain") {
          exec(getUserByEmail)
        }
      }
}
