import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.util.concurrent.TimeUnit

class MainMenuScene(game: Game) : Scene(game) {

    private val primaryFont = Font("Default", Font.BOLD, 30)
    private val secondaryFont = Font("Default", Font.PLAIN, 20)

    override fun update(timePassed: Long, timeUnits: TimeUnit) {
        game.input.consumeEvents().forEach {
            if (it is Input.Event.KeyPressed && it.data.keyCode == KeyEvent.VK_ENTER) {
                game.scene = GameScene(game)
            }
        }
    }

    override fun draw(graphics: Graphics2D) {
        graphics.apply {
            color = Color.black
            fillRect(0, 0, game.screenSize.width, game.screenSize.height)

            font = primaryFont
            color = Color.white
            val name = "Snake"

            drawString(
                name,
                game.screenSize.width / 2 - fontMetrics.stringWidth(name) / 2,
                game.screenSize.height / 2 - 50
            )

            font = secondaryFont
            color = Color.gray
            val message = "Press Enter to continue"

            drawString(
                message,
                game.screenSize.width / 2 - fontMetrics.stringWidth(message) / 2,
                game.screenSize.height / 2 + 50
            )
        }
    }
}