package com.idler.api.request.user

import com.idler.api.request.user.GetUserProfile.getUserProfile
import com.idler.config.Config.scenarioType
import com.idler.util.Utils.returnResponseBodyOnError
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object SignInUser {
  private[idler] val signInUser: ChainBuilder =
  //    feed(jsonFile(jsonUsersFile).circular)
    exec(
      http("SignInUser")
        .post("/api/v1/user/sign-in")
        .body(StringBody(
          """{
            "email":"${email}",
            "password":"qwerty"
            }"""
        )).asJson
        .check(status.is(200))
        .check(jsonPath("$.token").notNull.saveAs("token"))
    )
      .doIfOrElse(session => session.status.name == "KO") {
        exec(returnResponseBodyOnError("Sign in user failed"))
          .exitHere
      } {
        doIf(scenarioType == "chain") {
          exec(getUserProfile)
        }
      }
}

