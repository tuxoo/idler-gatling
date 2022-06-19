package com.idler.util.postgres

import java.time.LocalDateTime
import java.util.UUID

case class User(
                 id: UUID,
                 name: String,
                 loginEmail: String,
                 passwordHash: String,
                 registeredAt: LocalDateTime,
                 visitedAt: LocalDateTime,
                 role: String,
                 isEnabled: Boolean
               )
