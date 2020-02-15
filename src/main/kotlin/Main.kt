import java.awt.Dimension

fun main() {
    val game = GameFactory.create(
        screenSize = Dimension(660, 660),
        windowTitle = "Snake"
    )

    game.scene = MainMenuScene(game)

    game.play()
}