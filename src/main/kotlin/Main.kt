
import objects.Tool
import objects.Individual
import simpleFunction.Cruza
import utils.GenConstants


fun main() {
    val mask: IntArray = Tool.generateArray(GenConstants.HUMAN)
    val genMother: IntArray = Tool.generateArray(GenConstants.HUMAN)
    val genFather: IntArray = Tool.generateArray(GenConstants.HUMAN)
    val mother = Individual(genMother)
    val father = Individual(genFather)
    val son1 = Cruza.cruzaMascara(mother, father, mask)
    println()
}