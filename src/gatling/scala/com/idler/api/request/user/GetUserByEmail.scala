package com.idler.api.request.user

import com.idler.api.request.user.GetAllUsers.getAllUsers
import com.idler.config.Config.{jsonUsersFile, scenarioType}
import com.idler.util.Utils.{returnResponseBodyOnError, sessionCounter}
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object GetUserByEmail {
  val sessionHeaders: Map[String, String] = Map("Authorization" -> "Bearer ${token}")

  private[idler] val getUserByEmail: ChainBuilder =
    doIf(scenarioType == "one_request") {
      exec(sessionCounter.collectNewSession)
    }
      .exec(
        http("GetNonExistUserByEmail")
          .get("/api/v1/user/ElonMusk@tesla.com")
          .headers(sessionHeaders)
          .check(status.is(404))
      )
      .exec(
        http("GetUserByEmail")
          .get("/api/v1/user/${email}")
          .headers(sessionHeaders)
          .check(status.is(200))
      )
      .doIfOrElse(session => session.status.name == "KO") {
        exec(returnResponseBodyOnError("Gets user by email failed"))
          .exitHere
      } {
        doIf(scenarioType == "chain") {
          exec(getAllUsers)
        }
      }
}
