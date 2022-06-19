package com.idler.api.request.user

import com.idler.api.request.InsertingUsers.objectMapper
import com.idler.config.Config.{genUsersCount, jsonUsersFile, openUsersCount}

import java.io.{BufferedWriter, File, FileWriter}

object GenerateUsers {
  def main(args: Array[String]): Unit = {
    val file = new File(jsonUsersFile)

    case class NewUser(
                        name: String,
                        email: String,
                        password: String,
                      )

    if (file.exists()) file.delete()

    val writer: BufferedWriter = new BufferedWriter(new FileWriter(file, true))

    writer.append("[")
    writer.newLine()
    for (num <- 0 until genUsersCount) {
      val newUser = NewUser(
        name = s"User.${num}",
        email = s"Email.${num}@test.com",
        password = "qwerty"
      )
      writer.append(objectMapper.writeValueAsString(newUser))
      if (num != genUsersCount - 1) writer.append(",")
      writer.newLine()
    }
    writer.append("]")
    writer.close()
  }
}
