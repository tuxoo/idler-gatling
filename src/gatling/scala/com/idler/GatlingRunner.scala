package com.idler

import com.idler.api.simulation.user.SignUpUser
import com.idler.config.Config.dao
import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object GatlingRunner {
  def main(args: Array[String]): Unit = {

    dao.prepareDb()

    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[SignUpUser].getName)
    Gatling.fromMap(props.build)
  }
}
