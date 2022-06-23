package com.idler

import com.idler.api.simulation.user.{SignInUser, SignUpUser}
import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object GatlingRunner {
  def main(args: Array[String]): Unit = {

    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[SignInUser].getName)
    Gatling.fromMap(props.build)
  }
}
