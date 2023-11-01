import com.mipt.kotlin.ktor.practice.api.commentsApi
import com.mipt.kotlin.ktor.practice.repository.impl.DefaultCommentsRepository
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080) {
        configureServer()

        commentsApi()
    }.start(wait = true)
}

fun Application.configureServer() {
    install(Koin) {
        modules(commentsModule)
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }
}