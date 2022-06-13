package com.idler.util.postgres

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

class PostgresDao(db: Database) extends DatabaseSchema {

  def prepareDb(): Future[Unit] = {
    val query = DBIO.seq(
      users.delete,
    )
    db.run(query)
  }

  def saveUser(user: User): Future[Int] = {
    val query = users += user
    db.run(query)
  }
}
