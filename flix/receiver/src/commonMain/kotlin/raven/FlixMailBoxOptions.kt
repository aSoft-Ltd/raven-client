package raven

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel

class FlixMailBoxOptions(
    val url: String,
    val scope: CoroutineScope
) {
    val endpoint by lazy { FlixMailEndpoint(url) }
}