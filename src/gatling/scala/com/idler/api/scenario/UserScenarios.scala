package com.idler.api.scenario

import com.idler.api.request.user.GetAllUsers.getAllUsers
import com.idler.api.request.user.GetProfile.getUserProfile
import com.idler.api.request.user.GetUserByEmail.getUserByEmail
import com.idler.api.request.user.SignInUser.signInUser
import com.idler.config.Config.LOOP
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class UserScenarios {
  def userScn(scn: Int): ScenarioBuilder =
    scn match {
      case 1 =>
        scenario("SignInUser")
          .repeat(LOOP) {
            signInUser
          }
      case 2 =>
        scenario("GetUserProfile")
          .repeat(LOOP) {
            getUserProfile
          }
      case 3 =>
        scenario("GetAllUsers")
          .repeat(LOOP) {
            getAllUsers
          }
      case 4 =>
        scenario("GetUserByEmail")
          .repeat(LOOP) {
            getUserByEmail
          }
    }
}
