import java.awt.Dimension
import javax.swing.WindowConstants
import java.awt.BorderLayout
import javax.swing.JFrame

object GameFactory {

    fun create(
        screenSize: Dimension,
        windowTitle: String
    ): Game {
        val game = Game(screenSize)

        JFrame().apply {
            title = windowTitle
            isVisible = true

            layout = BorderLayout()
            add(game)
            pack()

            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            setLocationRelativeTo(null)
        }

        game.createBufferStrategy(2)
        game.requestFocus()
        return game
    }
}