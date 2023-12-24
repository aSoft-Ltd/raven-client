package raven

import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import koncurrent.TODOLater
import koncurrent.later
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class FlixMailbox(private val options: FlixMailBoxOptions) : MailBox {

    val messages = MutableStateFlow<String?>(null)

    init {
        val source = EventSource(options.endpoint.mailbox())
        source.on("message") {
            options.scope.launch {
                messages.emit(it.data)
                delay(1.seconds)
                messages.emit(null)
            }
        }
    }

    override fun anticipate(): Later<String> {
        val current = messages.value
        return options.scope.later { messages.filterNotNull().filter { it != current }.first() }
    }

    override fun save(message: EmailMessage): Later<EmailMessage> = TODOLater()

    override fun load(): Later<List<EmailMessage>> = TODOLater()
}