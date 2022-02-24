
import objects.Tool
import objects.Individual
import simpleFunction.*
import utils.GenConstants


fun main() {
    val aux = SimpleGenetic(2, 1000, 0.1)
    aux.evolve() // evolucionar
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
     */
    println()
}