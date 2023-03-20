package at.xirado.githook.service.rest.message

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend fun ApplicationCall.respondUnauthorized() =
    respondText("Unauthorized", ContentType.parse("text/plain"), HttpStatusCode.Unauthorized)

suspend fun ApplicationCall.respondOk() =
    respondText("Ok", ContentType.parse("text/plain"), HttpStatusCode.OK)

suspend fun ApplicationCall.respondThrowable(throwable: Throwable) =
    respondText(throwable.message ?: "Trigger failed", ContentType.parse("text/plain"), HttpStatusCode.BadRequest)
