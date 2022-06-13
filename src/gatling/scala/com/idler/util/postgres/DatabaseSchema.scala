package com.idler.util.postgres

import slick.jdbc.PostgresProfile.api._

import java.time.LocalDateTime

trait DatabaseSchema {

  class Users(tag: Tag) extends Table[User](tag, "user") {
    def name = column[String]("name")

    def email = column[String]("email")

    def passwordHash = column[String]("password_hash")

    def registeredAt = column[LocalDateTime]("registered_at")

    def visitedAt = column[LocalDateTime]("visited_at")

    def isConfirmed = column[Boolean]("is_confirmed")

    def * = (
      name,
      email,
      passwordHash,
      registeredAt,
      visitedAt,
      isConfirmed
    ) <> (User.tupled, User.unapply)
  }

  val users = TableQuery[Users]

}
