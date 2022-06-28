package com.idler.api.request.user

import com.idler.api.request.user.GetUserProfile.getUserProfile
import com.idler.config.Config.{connectionUrl, password, scenarioType, user}
import com.idler.util.Utils.returnResponseBodyOnError
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef.jdbcFeeder

object SignInUser {
  "jdbc:postgresql://localhost:5432/kbase?currentSchema=kbase&user=kbase&password=qwerty"

  private[idler] val signInUser: ChainBuilder =
    feed(jdbcFeeder(connectionUrl, user, password, "SELECT login_email FROM \"user\"").circular)
      .exec(
        http("SignInUser")
          .post("/api/v1/user/sign-in/")
          .body(StringBody(
            """{
            "email":"${login_email}",
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

