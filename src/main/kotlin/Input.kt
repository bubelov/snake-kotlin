import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Input : KeyListener {

    private val events = mutableListOf<Event>()

    override fun keyTyped(event: KeyEvent) {
        // We're not interested in this kind of events
    }

    override fun keyPressed(event: KeyEvent) {
        synchronized(this) {
            events += Event.KeyPressed(event)
        }
    }

    override fun keyReleased(event: KeyEvent) {
        synchronized(this) {
            events += Event.KeyReleased(event)
        }
    }

    fun consumeEvents(): List<Event> {
        synchronized(this) {
            val consumedEvents = events.toList()
            events.clear()
            return consumedEvents
        }
    }

    sealed class Event {
        data class KeyPressed(val data: KeyEvent) : Event()
        data class KeyReleased(val data: KeyEvent) : Event()
    }
}