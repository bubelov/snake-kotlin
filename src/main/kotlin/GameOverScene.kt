import java.awt.Color
import java.awt.Font
import java.awt.Font.BOLD
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.util.concurrent.TimeUnit

class GameOverScene(game: Game) : Scene(game) {

    override fun update(timePassed: Long, timeUnits: TimeUnit) {
        game.input.consumeEvents().forEach {
            when (it) {
                is Input.Event.KeyPressed -> {
                    when (it.data.keyCode) {
                        KeyEvent.VK_ENTER -> game.scene = GameScene(game)
                    }
                }
            }
        }
    }

    override fun draw(graphics: Graphics2D) {
        graphics.apply {
            color = Color.black
            fillRect(0, 0, game.screenSize.width, game.screenSize.height)

            font = Font("Default", BOLD, 16)
            color = Color.white

            val message = "Press <Enter> to start new game"
            val messageBounds = fontMetrics.getStringBounds(message, this)
            val messageWidth = messageBounds.width.toInt()
            val messageHeight = messageBounds.height.toInt()

            drawString(
                message,
                game.screenSize.width / 2 - messageWidth / 2,
                game.screenSize.height / 2 - messageHeight / 2
            )
        }
    }
}