package objects.tcp

class Individual {

    var chromosome: IntArray
        private set

    var fitness = -1.0

    constructor(chromosome: IntArray) {
        // Create individualchromosome
        this.chromosome = chromosome
    }

    constructor(chromosomeLength: Int) {
        // Create random individual
        val individual = IntArray(chromosomeLength)
        for (gene in 0 until chromosomeLength) {
            individual[gene] = gene
        }
        chromosome = individual
    }

    fun getChromosomeLength(): Int {
        return chromosome.size
    }

    fun setGene(offset: Int, gene: Int) {
        chromosome[offset] = gene
    }

    fun getGene(offset: Int): Int {
        return chromosome[offset]
    }

    override fun toString(): String {
        var output = ""
        for (gene in chromosome.indices) {
            output += chromosome[gene].toString() + ","
        }
        return output
    }

    fun containsGene(gene: Int): Boolean {
        for (i in chromosome.indices) {
            if (chromosome[i] == gene) {
                return true
            }
        }
        return false
    }

}