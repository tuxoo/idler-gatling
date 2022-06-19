package com.idler.config

import com.idler.util.postgres.PostgresDao
import slick.jdbc.JdbcBackend.Database

object Config {
  private[idler] val jsonUsersFile = "src/gatling/resources/api/users.json"

  private[idler] val baseUrl: String = System.getProperty("Url", "http://localhost:8080")
//  private[idler] val baseUrl: String = System.getProperty("Url", "http://localhost:9000")
  private[idler] val genericPasswordHash: String = System.getProperty("passwordhash", "b1b3773a05c0ed0176787a4f1574ff0075f7521e")
  private[idler] val RPS: Double = System.getProperty("Rps", "1").toInt
//  private[idler] val RPS: Double = System.getProperty("Rps", "10").toInt
  private[idler] val LOOP: Int = System.getProperty("Loop", "1").toInt
  private[idler] val ramp: Int = System.getProperty("Ramp", "30").toInt
  private[idler] val openScnDuration: Int = System.getProperty("Duration", "10").toInt
  private[idler] val closedScnDuration: Int = System.getProperty("Duration", "1").toInt
//  private[idler] val scnDuration: Int = System.getProperty("Duration", "10").toInt

  private[idler] val scenarioType: String = System.getProperty("Scenario", "chain")
//  private[idler] val scenarioType: String = System.getProperty("Scenario", "one_request")

//  private[idler] val connectionUrl = "jdbc:postgresql://localhost:5432/kbase?currentSchema=kbase&user=kbase&password=qwerty"
  private[idler] val connectionUrl = "jdbc:postgresql://localhost:5432/idler?currentSchema=idler&user=idler&password=qwerty"
//  private[idler] val connectionUrl = "jdbc:postgresql://localhost:5432/mmark?currentSchema=mmark&user=mmark&password=qwerty"
  private[idler] val db = Database.forURL(connectionUrl, driver = "org.postgresql.Driver")
  private[idler] val dao = new PostgresDao(db)

  private[idler] val openUsersCount: Int = System.getProperty("Users", "1000").toInt
  private[idler] val genUsersCount: Int = System.getProperty("Users", "100000").toInt
}
