package com.idler.config

object Config {
  private[idler] val LOOP: Int = System.getProperty("Loop", "1").toInt
}
