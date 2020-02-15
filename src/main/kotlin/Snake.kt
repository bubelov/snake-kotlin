class Snake(
    startX: Int,
    startY: Int,
    var direction: Direction = Direction.RIGHT
) {

    val body = mutableListOf<SnakeBodyPart>()
    val head by lazy { body[0] }

    init {
        body += SnakeBodyPart(startX, startY)

        body += SnakeBodyPart(
            x = startX - direction.deltaX(),
            y = startY - direction.deltaY()
        )

        body += SnakeBodyPart(
            x = startX - direction.deltaX() * 2,
            y = startY - direction.deltaY() * 2
        )
    }

    fun move() {
        for (i in body.size - 1 downTo 1) {
            val current = body[i]
            val (x, y) = body[i - 1]
            current.x = x
            current.y = y
        }

        head.x = head.x + direction.deltaX()
        head.y = head.y + direction.deltaY()
    }
}