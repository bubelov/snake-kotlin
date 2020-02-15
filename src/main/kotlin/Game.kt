import kotlinx.coroutines.*
import kotlinx.coroutines.swing.Swing
import java.awt.Canvas
import java.awt.Dimension
import java.awt.Graphics2D

class Game(val screenSize: Dimension) : Canvas() {

    var scene: Scene? = null

    val input = Input()

    private var gameLoop: Job? = null

    init {
        size = screenSize
        addKeyListener(input)
    }

    fun play() {
        if (gameLoop != null) return

        gameLoop = GlobalScope.launch {
            var lastIterationTime = System.nanoTime()

            while (isActive) {
                val scene = scene ?: continue
                val now = System.nanoTime()
                val timePassed = now - lastIterationTime
                lastIterationTime = now

                scene.update(timePassed)

                withContext(Dispatchers.Swing) {
                    scene.draw(bufferStrategy.drawGraphics as Graphics2D)
                    bufferStrategy.show()
                }
            }
        }
    }

    fun pause() = runBlocking {
        gameLoop?.cancel()
        gameLoop?.join()
        gameLoop = null
    }
}