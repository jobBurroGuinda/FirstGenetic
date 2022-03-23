package objects.tcp

class City(
    val x: Int,
    var y: Int
) {

    fun distanceFrom(city: City): Double {
        // Give difference in x,y
        val deltaXSq = Math.pow((city.x - x).toDouble(), 2.0)
        val deltaYSq = Math.pow((city.y - y).toDouble(), 2.0)

        // Calculate shortest path
        return Math.sqrt(Math.abs(deltaXSq + deltaYSq))
    }

}