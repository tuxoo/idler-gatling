package com.idler.api.scenario

import com.idler.api.request.user.GetAllUsers.getAllUsers
import com.idler.api.request.user.GetUserByEmail.getUserByEmail
import com.idler.api.request.user.GetUserProfile.getUserProfile
import com.idler.api.request.user.SignInUser.signInUser
import com.idler.api.request.user.SignUpUser.signUpUser
import com.idler.api.request.user.VerifyUser.verifyUser
import com.idler.config.Config.LOOP
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class UserScenarios {
  def userScn(scn: Int): ScenarioBuilder =
    scn match {
      case 1 =>
        scenario("SignUpUser")
          .repeat(LOOP) {
            signUpUser
          }
      case 2 =>
        scenario("VerifyUser")
          .repeat(LOOP) {
            verifyUser
          }
      case 3 =>
        scenario("SignInUser")
          .repeat(LOOP) {
            signInUser
          }
      case 4 =>
        scenario("GetUserProfile")
          .repeat(LOOP) {
            getUserProfile
          }
      case 5 =>
        scenario("GetAllUsers")
          .repeat(LOOP) {
            getAllUsers
          }
      case 6 =>
        scenario("GetUserByEmail")
          .repeat(LOOP) {
            getUserByEmail
          }
    }
}
