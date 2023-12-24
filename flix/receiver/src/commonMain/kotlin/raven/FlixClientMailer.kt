package raven

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kollections.List
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import koncurrent.later

class FlixClientMailer(private val options: FlixClientMailerOptions) : Mailer {
    override fun send(draft: EmailDraft, from: AddressInfo, to: List<AddressInfo>): Later<EmailMessage> = options.scope.later {
        val client = options.client
        val message = draft.toMessage(from, to)
        client.post(options.endpoint.send()) { setBody(message.toString()) }
        message
    }
}