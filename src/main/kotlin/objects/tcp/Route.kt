package objects.tcp

class Route(individual: Individual, cities: Array<City?>) {
	private val route: Array<City?>// Loop over cities in route and calculate route distance

	var distance = 0.0
		get() {
			if (field > 0) {
				return field
			}
			// Loop over cities in route and calculate route distance
			var totalDistance = 0.0
			var cityIndex = 0
			while (cityIndex + 1 < route.size) {
				totalDistance += route[cityIndex]!!.distanceFrom(route[cityIndex + 1]!!)
				cityIndex++
			}
			totalDistance += route[route.size - 1]!!.distanceFrom(route[0]!!)
			field = totalDistance
			return totalDistance
		}
		private set

	init {
		// Get individual's chromosome
		val chromosome = individual.chromosome
		// Create route
		route = arrayOfNulls(cities.size)
		for (geneIndex in chromosome.indices) {
			route[geneIndex] = cities[chromosome[geneIndex]]
		}
	}
}