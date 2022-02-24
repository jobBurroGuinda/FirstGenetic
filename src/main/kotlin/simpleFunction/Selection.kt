package simpleFunction

import objects.Individual
import kotlin.random.Random

object Selection {

    fun selectionByTournament(population:MutableList<Individual>):Individual{
        var better = population[0]
        for(i in population){
            if(i.fitness > better.fitness){
                better = i
            }
        }
        return Individual(better.genotype)
    }

    fun selectionByRandom(population:MutableList<Individual>):Individual{
        val pos = Random.nextInt(population.size)
        return Individual(population[pos].genotype)
    }

}