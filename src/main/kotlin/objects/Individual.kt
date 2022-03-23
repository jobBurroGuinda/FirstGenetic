package objects

import utils.GenConstants
import kotlin.math.pow
import kotlin.properties.Delegates

class Individual {

    var genotype: IntArray
    var phenotype by Delegates.notNull<Int>()
    var fitness by Delegates.notNull<Int>()

    constructor() {
        genotype = Tool.generateArray(GenConstants.HUMAN)
        toCalculatePhenotype()
        toCalculateFitness()
    }

    // creación aleatoria
    constructor(genotype: IntArray) {
        this.genotype = genotype.clone()
        toCalculatePhenotype()
        toCalculateFitness()
    }

    private fun toCalculatePhenotype() {
        phenotype = binaryToDecimal(genotype)
    }

    private fun toCalculateFitness() {
        fitness = 2 * phenotype
    }

    private fun binaryToDecimal(binary:IntArray): Int {
        var decimal = 0
        //println("Genotype: ${binaryToString(genotype)}\n")
        for ((counter, i) in (binary.size-1 downTo 0).withIndex()) {
        println("\ti=$i counter=$counter binary=${binary[i]}")
            val multiplier = 2.0.pow(counter.toDouble())
        println("\t\tmultiplier(${multiplier.toInt()}) = (2^counter(${counter}) = ${multiplier.toInt()})")
            decimal += (binary[i] * multiplier).toInt()
        println("\t\tdecimal(${decimal}) += (binary(${binary[i]}) * multiplier(${multiplier.toInt()}) = ${binary[i]*multiplier.toInt()})")
        }
        println("\nPhenotype = decimal = $decimal")
        println("Fitness = 2 * phenotype = ${decimal*2}")
        return decimal
    }

     fun binaryToString(binary:IntArray):String{
        var string = ""
        for(i in binary.indices){
            string += "${binary[i]}"
        }
        return string
    }

     fun update(){
        toCalculatePhenotype()
        toCalculateFitness()
    }

}