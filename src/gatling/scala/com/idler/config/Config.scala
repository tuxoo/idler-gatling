package com.idler.config

import com.idler.util.postgres.PostgresDao
import com.typesafe.config.{Config, ConfigFactory}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.language.postfixOps

object Config {
  private[idler] val jsonUsersFile = "src/gatling/resources/api/users.json"
  private[idler] val baseUrl: String = System.getProperty("Url", "http://localhost:9000")
  private[idler] val LOOP: Int = System.getProperty("Loop", "1").toInt
  private[idler] val scenarioType: String = System.getProperty("Scenario", "chain")

  private[idler] val applicationConf: Config = ConfigFactory.load("application.conf")

  private[idler] val host: String = applicationConf.getString("postgres.properties.serverName")
  private[idler] val port: String = applicationConf.getString("postgres.properties.portNumber")
  private[idler] val databaseName: String = applicationConf.getString("postgres.properties.databaseName")
  private[idler] val user: String = applicationConf.getString("postgres.properties.user")
  private[idler] val password: String = applicationConf.getString("postgres.properties.password")
  private[idler] val schema: String = applicationConf.getString("postgres.properties.currentSchema")
  private[idler] val connectionUrl: String = s"jdbc:postgresql://${host}:${port}/${databaseName}?currentSchema=${schema}"
  private[idler] val db: Database = Database.forConfig("postgres")
  private[idler] val dao: PostgresDao = new PostgresDao(db)

  private[idler] val usersPerSec: Int = 30
  private[idler] val times: Int = 90
  private[idler] val levelLasting: FiniteDuration = 5 seconds
  private[idler] val rampsLasting: FiniteDuration = 5 seconds
  private[idler] val maxScnDuration: FiniteDuration = 15 minutes

  private[idler] val openUsersCount: Int = System.getProperty("OpenUsers", "800").toInt
  private[idler] val closedUsersCount: Int = System.getProperty("ClosedUsers", "20000").toInt
  private[idler] val genUsersCount: Int = System.getProperty("Users", "3000000").toInt
}
