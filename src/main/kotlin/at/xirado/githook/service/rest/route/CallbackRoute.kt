package at.xirado.githook.service.rest.route

import at.xirado.githook.action.ActionChain
import at.xirado.githook.entity.WorkflowBody
import at.xirado.githook.service.rest.RestService
import at.xirado.githook.service.rest.message.respondOk
import at.xirado.githook.service.rest.message.respondThrowable
import at.xirado.githook.service.rest.message.respondUnauthorized
import at.xirado.githook.util.Hmac
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

private val json = Json { ignoreUnknownKeys = true }

context(RestService)
fun Routing.callbackRoute() = post("/callback") {
    val body = call.receiveText()

    val signature = call.request.header("X-Hub-Signature-256")?.removePrefix("sha256=")
        ?: return@post call.respondUnauthorized()

    val expected = Hmac.digest(body, config.rest.secret)

    if (signature != expected)
        return@post call.respondUnauthorized()

    log.info("Got verified event")

    val bodyParsed = json.decodeFromString(WorkflowBody.serializer(), body)

    val branchName = bodyParsed.ref?.removePrefix("refs/heads/")

    log.info("Branch: $branchName")

    val triggers = triggers.filter { trigger -> trigger.repository == bodyParsed.repository?.fullName && trigger.branch?.any { it == branchName } ?: true }

    log.info("Found ${triggers.size} triggers")

    try {
        triggers.forEach { trigger ->
            ActionChain(null, null, trigger.action ?: emptyList()).begin()
        }
    } catch (throwable: Throwable) {
        log.info("Trigger failed", throwable)
        return@post call.respondThrowable(throwable)
    }

    call.respondOk()
}