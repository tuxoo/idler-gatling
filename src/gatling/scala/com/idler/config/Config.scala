package com.idler.config

import com.idler.util.postgres.PostgresDao
import slick.jdbc.JdbcBackend.Database

object Config {
  private[idler] val jsonUsersFile = "api/users.json"

  private[idler] val baseUrl: String = System.getProperty("Url", "http://localhost:8080")
  private[idler] val genericPasswordHash: String = System.getProperty("passwordhash", "3162383261326264b1b3773a05c0ed0176787a4f1574ff0075f7521e")
  private[idler] val RPS: Double = System.getProperty("Rps", "200").toInt
  private[idler] val LOOP: Int = System.getProperty("Loop", "1").toInt
  private[idler] val ramp: Int = System.getProperty("Ramp", "10").toInt
  private[idler] val scnDuration: Int = System.getProperty("Duration", "100").toInt

  private[idler] val scenarioType: String = System.getProperty("Scenario", "chain")
//  private[idler] val scenarioType: String = System.getProperty("Scenario", "one_request")

  private[idler] val connectionUrl = "jdbc:postgresql://localhost:5432/idler?currentSchema=idler&user=idler&password=qwerty"
  private[idler] val db = Database.forURL(connectionUrl, driver = "org.postgresql.Driver")
  private[idler] val dao = new PostgresDao(db)

  private[idler] val usersCount: Int = System.getProperty("Users", "10000").toInt
}
