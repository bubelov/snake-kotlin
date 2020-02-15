class Timer(private val duration: Long) {

    private var remainingTime = duration

    fun update(timePassed: Long) {
        remainingTime -= timePassed
    }

    fun timeIsUp() = remainingTime <= 0

    fun reset() {
        remainingTime = duration
    }
}