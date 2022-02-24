package simpleFunction

import objects.Individual
import objects.Tool
import utils.GenConstants

class SimpleGenetic(_sizePopulation: Int, _noGenerations: Int, _probMutate: Double) {

    var sizePopulation = _sizePopulation
    var noGenerations = _noGenerations
    var probMutate = _probMutate
    var population:MutableList<Individual>

    init {
        population = mutableListOf()
        generateInitialPopulation()
    }

    private fun generateInitialPopulation() {
        // se genere de manera aleatoria
        for(x in 0 until sizePopulation){
            population.add(Individual())
        }
    }


    fun evolve() {
        var pobAux:MutableList<Individual>
        val mask = Tool.generateRandomMask(GenConstants.HUMAN)
        var betterGeneration = 0
        // Someter a la población a un proceso evolutivo
        for(g in 0 until noGenerations){
            // crear una población nueva
            pobAux = mutableListOf()
            for(i in 0 until sizePopulation){
                // muestreo y/O selección
                // torneo
                val mother = Selection.selectionByTournament(population)
                val father = Selection.selectionByRandom(population)
                val child = Cruza.cruzaMascara(mother, father, mask)
                // cruza
                // evaluar la posibilidad de muta
                if(Mutate.evalueMutate(probMutate)){
                    Mutate.mutate(child)
                }
                // agregar hijo a la población auxiliar
                pobAux.add(child)
            }
            // se tiene que actualizar a la población
            updatePopulation(pobAux)
            val better = Selection.selectionByTournament(population)
            print("g${g+1}: ${better.binaryToString(better.genotype)}  f: ${better.phenotype}") // maximo fenotipo para e genotipo humano: 16777215
            if(better.phenotype == GenConstants.HUMAN_MAX_VALUE_GENOTYPE && betterGeneration == 0) {
                betterGeneration = g+1
                print(" --> g${g+1}: MEJOR GENERACIÓN")
            }
            println()
        }
        if(betterGeneration > 0) println("\nMejor generación: $betterGeneration")
        else println("\nNo se encontró la mejor generación\n" +
                "Pruebe aumentando la cantidad de generaciones,\n o la probabilidad de muta\n")
    }

    private fun updatePopulation(pobAux: MutableList<Individual>) {
        population = mutableListOf()
        for(i in pobAux){
            population.add(Individual(i.genotype))
        }
    }
}