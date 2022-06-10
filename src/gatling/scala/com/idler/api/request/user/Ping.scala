package com.idler.api.request.user

import io.gatling.http.Predef.{http, status}
import io.gatling.http.request.builder.HttpRequestBuilder

object Ping {
  private[idler] val ping: HttpRequestBuilder = http("Ping")
    .get("/api/v1/ping")
    .check(status.is(200))
    .check()
}
