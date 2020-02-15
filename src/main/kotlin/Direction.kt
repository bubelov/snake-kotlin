enum class Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    fun deltaX(): Int {
        return when (this) {
            LEFT -> -1
            RIGHT -> 1
            else -> 0
        }
    }

    fun deltaY(): Int {
        return when (this) {
            UP -> 1;
            DOWN -> -1;
            else -> 0;
        }
    }
}