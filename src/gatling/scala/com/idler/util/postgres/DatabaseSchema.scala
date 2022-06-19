package com.idler.util.postgres

import slick.jdbc.PostgresProfile.api._

import java.time.LocalDateTime
import java.util.UUID

trait DatabaseSchema {

  class Users(tag: Tag) extends Table[User](tag, "user") {
    def id = column[UUID]("id")

    def name = column[String]("name")

    def loginEmail = column[String]("login_email")

    def passwordHash = column[String]("password_hash")

    def registeredAt = column[LocalDateTime]("registered_at")

    def visitedAt = column[LocalDateTime]("visited_at")

    def role = column[String]("role")

    def isEnabled = column[Boolean]("is_enabled")

    def * = (
      id,
      name,
      loginEmail,
      passwordHash,
      registeredAt,
      visitedAt,
      role,
      isEnabled
    ) <> (User.tupled, User.unapply)
  }

  val users = TableQuery[Users]

}
