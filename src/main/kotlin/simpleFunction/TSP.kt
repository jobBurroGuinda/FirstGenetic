package simpleFunction

import objects.tcp.City
import objects.tcp.Individual
import objects.tcp.Population
import objects.tcp.Route
import java.util.*


open class TSP(
    private val populationSize: Int,
    private val mutationRate: Double,
    private val crossoverRate: Double,
    private val elitismCount: Int,
    private var tournamentSize: Int
) {
    fun initPopulation(chromosomeLength: Int): Population {
        // Initialize population
        return Population(populationSize, chromosomeLength)
    }

    fun isTerminationConditionMet(generationsCount: Int, maxGenerations: Int): Boolean {
        return generationsCount > maxGenerations
    }

    fun calcFitness(individual: Individual?, cities: Array<City?>?): Double {
        // Get fitness
        val route = individual?.let { Route(it, cities!!) }
        val fitness = 1 / route!!.distance

        // Store fitness
        individual.fitness = fitness
        return fitness
    }

    fun evalPopulation(population: Population, cities: Array<City?>?) {
        var populationFitness = 0.0

        // Loop over population evaluating individuals and summing population fitness
        for (individual in population.individuals) {
            populationFitness += calcFitness(individual, cities)
        }
        val avgFitness = populationFitness / population.size()
        population.populationFitness = avgFitness
    }

    fun selectParent(population: Population): Individual? {
        // Create tournament
        val tournament = Population(tournamentSize)

        // Add random individuals to the tournament
        population.shuffle()
        for (i in 0 until tournamentSize) {
            val tournamentIndividual: Individual? = population.getIndividual(i)
            if (tournamentIndividual != null) {
                tournament.setIndividual(i, tournamentIndividual)
            }
        }

        // Return the best
        return tournament.getFittest(0)
    }

    fun crossoverPopulation(population: Population): Population {
        // Create new population
        val newPopulation = Population(population.size())

        // Loop over current population by fitness
        for (populationIndex in 0 until population.size()) {
            // Get parent1
            val parent1: Individual? = population.getFittest(populationIndex)

            // Apply crossover to this individual?
            if (crossoverRate > Math.random() && populationIndex >= elitismCount) {
                // Find parent2 with tournament selection
                val parent2: Individual? = selectParent(population)

                // Create blank offspring chromosome
                val offspringChromosome = parent1?.let { IntArray(it.getChromosomeLength()) }
                Arrays.fill(offspringChromosome!!, -1)
                val offspring = offspringChromosome.let { Individual(it) }

                // Get subset of parent chromosomes
                val substrPos1 = (Math.random() * parent1.getChromosomeLength()).toInt()
                val substrPos2 = (Math.random() * parent1.getChromosomeLength()).toInt()

                // make the smaller the start and the larger the end
                val startSubstr = Math.min(substrPos1, substrPos2)
                val endSubstr = Math.max(substrPos1, substrPos2)

                // Loop and add the sub tour from parent1 to our child
                for (i in startSubstr until endSubstr) {
                    offspring.setGene(i, parent1.getGene(i))
                }

                // Loop through parent2's city tour
                if (parent2 != null) {
                    for (i in 0 until parent2.getChromosomeLength()) {
                        var parent2Gene = i + endSubstr
                        if (parent2Gene >= parent2.getChromosomeLength()) {
                            parent2Gene -= parent2.getChromosomeLength()
                        }

                        // If offspring doesn't have the city add it
                        if (offspring.containsGene(parent2.getGene(parent2Gene)) === false) {
                                // Loop to find a spare position in the child's tour
                                for (ii in 0 until offspring.getChromosomeLength()) {
                                    // Spare position found, add city
                                    if (offspring.getGene(ii) === -1) {
                                        offspring.setGene(ii, parent2.getGene(parent2Gene))
                                        break
                                    }
                                }
                            }
                    }
                }

                // Add child
                newPopulation.setIndividual(populationIndex, offspring)
            } else {
                // Add individual to new population without applying crossover
                if (parent1 != null) {
                    newPopulation.setIndividual(populationIndex, parent1)
                }
            }
        }
        return newPopulation
    }

    fun mutatePopulation(population: Population): Population {
        // Initialize new population
        val newPopulation = Population(populationSize)

        // Loop over current population by fitness
        for (populationIndex in 0 until population.size()) {
            val individual: Individual? = population.getFittest(populationIndex)

            // Skip mutation if this is an elite individual
            if (populationIndex >= elitismCount) {
                // System.out.println("Mutating population member "+populationIndex);
                // Loop over individual's genes
                if (individual != null) {
                    for (geneIndex in 0 until individual.getChromosomeLength()) {
                        // System.out.println("\tGene index "+geneIndex);
                        // Does this gene need mutation?
                        if (mutationRate > Math.random()) {
                            // Get new gene position
                            val newGenePos = (Math.random() * individual.getChromosomeLength()).toInt()
                            // Get genes to swap
                            val gene1: Int = individual.getGene(newGenePos)
                            val gene2: Int = individual.getGene(geneIndex)
                            // Swap genes
                            individual.setGene(geneIndex, gene1)
                            individual.setGene(newGenePos, gene2)
                        }
                    }
                }
            }

            // Add individual to population
            if (individual != null) {
                newPopulation.setIndividual(populationIndex, individual)
            }
        }

        // Return mutated population
        return newPopulation
    }
}