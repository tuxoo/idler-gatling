package com.idler.api.request.user

import com.idler.api.request.InsertingUsers.generateCode
import com.idler.util.Utils.returnResponseBodyOnError
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.language.postfixOps

object VerifyUser {
  private[idler] val verifyUser: ChainBuilder =
    exec { session =>
      val name = session("name").as[String]
      session.set("code", generateCode(name))
    }
      .exec(
        http("VerifyUser")
          .post("/api/v1/user/verify/")
          .body(StringBody(
            """{
            "email":"${email}",
            "checkCode":"${code}"
            }"""
          )).asJson
          .check(status.is(200)))
      .doIf(session => session.status.name == "KO") {
        exec(returnResponseBodyOnError("Sign in user failed"))
          .exitHere
      }
}

