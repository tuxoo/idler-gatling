package com.idler

import com.idler.api.simulation.PingSimulation
import com.idler.api.simulation.generation.GenerateUsers
import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object GatlingRunner {
  def main(args: Array[String]): Unit = {

    def start(className: String): String = {
      val nameOnly = className.split("\\.")
      className
    }

//    val simClass = start(classOf[PingSimulation].getName)
    val simClass = start(classOf[GenerateUsers].getName)
    val props = new GatlingPropertiesBuilder
    props.simulationClass(simClass)
    Gatling.fromMap(props.build)
  }
}
