package objects

import kotlin.math.pow

class Individual {

    var genotype: IntArray
    var phenotype = 0
    var fitness = 0

    constructor() {
        genotype = IntArray(24)
        phenotype = 0
        fitness = 0
    }

    // creación aleatoria
    constructor(genotipo: IntArray) {
        this.genotype = genotipo.clone()
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
        println("Genotype: ${binaryToString(genotype)}\n")
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

    private fun binaryToString(binary:IntArray):String{
        var string = ""
        for(i in binary.indices){
            string += "${binary[i]}"
        }
        return string
    }

}