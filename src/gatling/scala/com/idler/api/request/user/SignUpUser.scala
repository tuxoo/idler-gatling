package com.idler.api.request.user

import com.idler.api.request.user.GetAllUsers.getAllUsers
import com.idler.api.request.user.VerifyUser.verifyUser
import com.idler.config.Config.{jsonUsersFile, scenarioType}
import com.idler.util.Utils.returnResponseBodyOnError
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object SignUpUser {
  private[idler] val signUpUser: ChainBuilder =
    feed(jsonFile(jsonUsersFile).circular)
      .exec(
        http("SignUpUser")
          .post("/api/v1/user/sign-up")
          .body(StringBody(
            """{
            "name":"${name}",
            "email":"${email}",
            "password":"${password}"
            }"""
          )).asJson
          .check(status.is(201)
          )
      )
      .doIfOrElse(session => session.status.name == "KO") {
        exec(returnResponseBodyOnError("Sign up user failed"))
          .exitHere
      } {
        doIf(scenarioType == "chain") {
          exec(verifyUser)
        }
      }
}

