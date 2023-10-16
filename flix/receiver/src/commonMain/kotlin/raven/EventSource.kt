package raven

expect class EventSource {
    fun on(event: String, handler: (Event) -> Unit)
}