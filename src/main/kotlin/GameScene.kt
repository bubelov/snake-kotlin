import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.util.concurrent.TimeUnit

class GameScene(game: Game) : Scene(game) {

    private val snake = Snake(
        startX = WORLD_WIDTH / 2,
        startY = WORLD_HEIGHT / 2
    )

    private lateinit var apple: Apple

    private val snakeMoveTimer = Timer(TimeUnit.MILLISECONDS.toNanos(300))

    init {
        placeApple()
    }

    override fun update(timePassed: Long, timeUnits: TimeUnit) {
        if (gameIsOver()) {
            game.scene = GameOverScene(game)
            return
        }

        processInput()

        snakeMoveTimer.update(timePassed)

        if (snakeMoveTimer.timeIsUp()) {
            snake.move()
            val head = snake.head

            if (head.x < 1) {
                head.x = WORLD_WIDTH
            }

            if (head.x > WORLD_WIDTH) {
                head.x = 1
            }

            if (head.y < 1) {
                head.y = WORLD_HEIGHT
            }

            if (head.y > WORLD_HEIGHT) {
                head.y = 1
            }

            if (head.x == apple.x && head.y == apple.y) {
                val body = snake.body
                val lastPart = body[body.size - 1]
                body.add(SnakeBodyPart(lastPart.x, lastPart.y))
                placeApple()
            }

            snakeMoveTimer.reset()
        }
    }

    override fun draw(graphics: Graphics2D) {
        graphics.apply {
            color = Color.black
            fillRect(0, 0, game.width, game.height)
            drawSnake(this)
            drawApple(this)
        }
    }

    private fun processInput() {
        for (event in game.input.consumeEvents()) {
            when (event) {
                is Input.Event.KeyPressed -> {
                    when (event.data.keyCode) {
                        KeyEvent.VK_UP -> snake.direction = Direction.UP
                        KeyEvent.VK_RIGHT -> snake.direction = Direction.RIGHT
                        KeyEvent.VK_DOWN -> snake.direction = Direction.DOWN
                        KeyEvent.VK_LEFT -> snake.direction = Direction.LEFT
                    }
                }
            }
        }
    }

    private fun drawSnake(graphics: Graphics2D) {
        graphics.apply {
            color = Color.green

            snake.body.forEach { part ->
                fillRect(
                    part.x * CELL_SIZE - CELL_SIZE,
                    game.screenSize.height - part.y * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE
                )
            }
        }
    }

    private fun drawApple(graphics: Graphics2D) {
        graphics.apply {
            color = Color.red

            fillRect(
                apple.x * CELL_SIZE - CELL_SIZE,
                game.screenSize.height - apple.y * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE
            )
        }
    }

    private fun placeApple() {
        var x = (1 + (Math.random() * WORLD_WIDTH)).toInt()
        var y = (1 + (Math.random() * WORLD_HEIGHT)).toInt()

        while (!isCellEmpty(x, y)) {
            if (x < WORLD_WIDTH) {
                x++
            } else {
                if (y < WORLD_HEIGHT) {
                    x = 1
                    y++
                } else {
                    x = 1
                    y = 1
                }
            }
        }

        apple = Apple(x, y)
    }

    private fun isCellEmpty(x: Int, y: Int) = snake.body.none { it.x == x && it.y == y }

    private fun gameIsOver(): Boolean {
        if (snake.body.size == WORLD_WIDTH * WORLD_HEIGHT) {
            return true
        }

        snake.body.forEachIndexed { index, part ->
            if (index > 0 && part.x == snake.head.x && part.y == snake.head.y) {
                return true
            }
        }

        return false
    }

    companion object {
        const val WORLD_WIDTH = 12
        const val WORLD_HEIGHT = 12
        const val CELL_SIZE = 55
    }
}