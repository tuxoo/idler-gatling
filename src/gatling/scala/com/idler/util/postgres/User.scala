package com.idler.util.postgres

import java.time.LocalDateTime

case class User(name: String, email: String, passwordHash: String, registeredAt: LocalDateTime, visitedAt: LocalDateTime, isConfirmed: Boolean)
