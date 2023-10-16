package raven

import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope

class FlixClientMailerOptions(
    val url: String,
    val scope: CoroutineScope,
    val client: HttpClient
) {
    val endpoint by lazy { FlixMailEndpoint(url) }
}