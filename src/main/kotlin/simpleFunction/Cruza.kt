package simpleFunction

import objects.Individual
import java.util.*

object Cruza {

    // metodo de cruza en base a mascara, y retorna el mejor
    fun cruzaMascara(m: Individual, p: Individual, mascara: IntArray): Individual {
        val g1 = IntArray(m.genotype.size)
        val g2 = IntArray(m.genotype.size)
        // recorriendo la mascara
        for (x in mascara.indices) {
            // copias genotípicas en los genes de los hijos
            if (mascara[x] == 1) {
                g1[x] = m.genotype[x]
                g2[x] = p.genotype[x]
            } else {
                g1[x] = p.genotype[x]
                g2[x] = m.genotype[x]
            }
        }
        val h1 = Individual(g1)
        val h2 = Individual(g2)
        return if (h1.fitness > h2.fitness) h1 else h2
    }




}