package raven

import koncurrent.Later
import koncurrent.LaterPromise
import koncurrent.TODOLater
import kotlinx.coroutines.launch

class FlixMailbox(private val options: FlixMailBoxOptions) : MailBox {

    val channel = options.channel

    init {
        val source = EventSource(options.endpoint.mailbox())
        source.on("message") {
            options.scope.launch {
                anticipate?.resolveWith(it.data)
                channel.send(it.data)
            }
        }
    }

    private var anticipate: LaterPromise<String>? = null
    fun anticipate(): Later<String> {
        val mail = LaterPromise<String>()
        anticipate = mail
        return mail
    }

    override fun save(message: EmailMessage): Later<EmailMessage> = TODOLater()

    override fun load(): Later<List<EmailMessage>> = TODOLater()
}