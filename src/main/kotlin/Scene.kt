import java.awt.Graphics2D
import java.util.concurrent.TimeUnit

abstract class Scene(val game: Game) {

    abstract fun update(
        timePassed: Long,
        timeUnits: TimeUnit = TimeUnit.NANOSECONDS
    )

    abstract fun draw(graphics: Graphics2D)
}