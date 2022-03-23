package objects.tcp

import java.util.*


class Population {

    var individuals: Array<Individual?>
        private set

    var populationFitness = -1.0

    constructor(populationSize: Int) {
        // Initial population
        individuals = arrayOfNulls(populationSize)
    }

    constructor(populationSize: Int, chromosomeLength: Int) {
        // Initial population
        individuals = arrayOfNulls(populationSize)

        // Loop over population size
        for (individualCount in 0 until populationSize) {
            // Create individual
            val individual = Individual(chromosomeLength)
            // Add individual to population
            individuals[individualCount] = individual
        }
    }

    fun getFittest(offset: Int): Individual? {
        // Order population by fitness
        Arrays.sort(individuals, Comparator { o1, o2 ->
            if (o2 != null) {
                if (o1 != null) {
                    if (o1.fitness > o2.fitness) {
                        return@Comparator -1
                    } else if (o1.fitness < o2.fitness) {
                        return@Comparator 1
                    }
                }
            }
            0
        })

        // Return the fittest individual
        return individuals[offset]
    }

    fun size(): Int {
        return individuals.size
    }

    fun setIndividual(offset: Int, individual: Individual): Individual {
        return individual.also { individuals[offset] = it }
    }

    fun getIndividual(offset: Int): Individual? {
        return individuals[offset]
    }

    fun shuffle() {
        val rnd = Random()
        for (i in individuals.size - 1 downTo 1) {
            val index: Int = rnd.nextInt(i + 1)
            val a = individuals[index]
            individuals[index] = individuals[i]
            individuals[i] = a
        }
    }
}