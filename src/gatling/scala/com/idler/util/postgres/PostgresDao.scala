package com.idler.util.postgres

import slick.jdbc.PostgresProfile.api._

import java.util.UUID
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class PostgresDao(db: Database) extends DatabaseSchema {

  def prepareDb(): Int = {
    Await.result(db.run(users.delete), Duration.Inf)
  }

  def getUserByEmail(email: String): Future[UUID] = {
    val query = users.filter(user => user.loginEmail === email).map(_.id).take(1).result.head
    db.run(query)
  }

  def saveUser(user: User): Future[Int] = {
    val query = users += user
    db.run(query)
  }
}
