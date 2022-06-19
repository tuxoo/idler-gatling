package com.idler.api.request.user

import com.idler.api.request.InsertingUsers.getUserId
import com.idler.util.Utils.returnResponseBodyOnError
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.language.postfixOps

object VerifyUser {
  private[idler] val verifyUser: ChainBuilder =
    exec { session =>
      val email = session("email").as[String]
      session.set("id", getUserId(email))
    }
      .exec(
        http("VerifyUser")
          .post("/api/v1/user/verify/${id}")
          .check(status.is(200)))
      .doIf(session => session.status.name == "KO") {
        exec(returnResponseBodyOnError("Sign in user failed"))
          .exitHere
      }
}

