package com.idler.api.scenario

import com.idler.api.request.conversation.CreateConversation.createConversation
import com.idler.api.request.conversation.DeleteConversationById.deleteConversation
import com.idler.api.request.conversation.GetUserConversations.getUserConversations
import com.idler.config.Config.LOOP
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class ConversationScenarios {
  def conversationScn(scn: Int): ScenarioBuilder =
    scn match {
      case 1 =>
        scenario("GetUserConversations")
          .repeat(LOOP) {
            getUserConversations
          }
      case 2 =>
        scenario("CreateConversations")
          .repeat(LOOP) {
            createConversation
          }
      case 3 =>
        scenario("DeleteConversations")
          .repeat(LOOP) {
            deleteConversation
          }
    }
}
