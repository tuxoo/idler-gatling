package com.idler.api.request

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object PingRequest {
  val ping: HttpRequestBuilder = http("Ping")
    .get("/api/v1/ping")
    .check(status.is(200))
    .check()
}
