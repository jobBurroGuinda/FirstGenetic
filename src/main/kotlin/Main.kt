import objects.tcp.City
import objects.tcp.Route
import simpleFunction.TSP


fun main() {

    val maxGenerations = 10000

    // Create cities
    val numCities = 100
    val cities = arrayOfNulls<City>(numCities)

    // Loop to create random cities

    // Loop to create random cities
    for (cityIndex in 0 until numCities) {
        // Generate x,y position
        val xPos = (100 * Math.random()).toInt()
        val yPos = (100 * Math.random()).toInt()

        // Add city
        cities[cityIndex] = City(xPos, yPos)
    }


    // Initial GA
    val ga = TSP(100, 0.001, 0.9, 2, 5)


    // Initialize population
    var population = ga.initPopulation(cities.size)


    // Evaluate population
    ga.evalPopulation(population, cities)

    val startRoute = Route(population.getFittest(0)!!, cities)
    println("Distancia inicial: " + startRoute.distance)


    // Keep track of current generation
    var generation = 1
    // Start evolution loop
    while (!ga.isTerminationConditionMet(generation, maxGenerations)) {
        // Print fittest individual from population
        val route = Route(population.getFittest(0)!!, cities)
        println("g" + generation + " mejor distancia: " + route.distance)

        // Apply crossover
        population = ga.crossoverPopulation(population)

        // Apply mutation
        population = ga.mutatePopulation(population)

        // Evaluate population
        ga.evalPopulation(population, cities)

        // Increment the current generation
        generation++
    }

    println("\nDetenido después de $maxGenerations generaciones")
    val route = Route(population.getFittest(0)!!, cities)
    println("Mejor distancia: " + route.distance)

    /*
    val aux = SimpleGenetic(2, 1000, 0.1)
    aux.evolve() // evolucionar

     */
    /*val pob = mutableListOf<Individual>()
    pob.add(Individual())
    pob.add(Individual())
    pob.add(Individual())
    pob.add(Individual())
    pob.add(Individual())
    val better = Selection.selectionByTournament(pob)

     */
    //val g = Tool.generateArray(5)
    //val individual = Individual(g)
    //Muta.muta(individual)
    //Mutate.mutate(individual)
/*
    val mask: IntArray = Tool.generateRandomMask(GenConstants.HUMAN)
    val genMother: IntArray = Tool.generateArray(GenConstants.HUMAN)
    val genFather: IntArray = Tool.generateArray(GenConstants.HUMAN)
    val mother = Individual(genMother)
    val father = Individual(genFather)
    val child1 = Cruza.cruzaMascara(mother, father, mask)
    println()
    */
}