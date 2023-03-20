package at.xirado.githook.service.rest

import at.xirado.githook.service.GitHookConfigurable
import at.xirado.githook.service.rest.route.callbackRoute
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.athena.util.newCoroutineScope
import kotlin.properties.Delegates

class RestService : GitHookConfigurable {
    private val host = config.rest.host
    private val port = config.rest.port

    val triggers = config.trigger
    val scope = newCoroutineScope<RestService>()

    private var engine: NettyApplicationEngine by Delegates.notNull()

    @Suppress("ExtractKtorModule")
    override suspend fun onLoad() {
        engine = embeddedServer(Netty, host = host, port = port) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                })
            }

            routing {
                callbackRoute()
            }
        }.start(false)
    }

    override suspend fun onUnload() {
        engine.stop(0, 10000)
    }
}