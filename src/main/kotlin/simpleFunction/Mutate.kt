package simpleFunction

import objects.Individual
import java.util.*

object Mutate {

    fun mutate(i: Individual) {
        //int[] gen = i.getGenotype().clone();
        // necesitamos una posición para poder modificar
        val ran = Random()
        val pos = ran.nextInt(i.genotype.size)
        // Se modifica el valor del fenotipo
        if (i.genotype[pos] == 0) {
            i.genotype[pos] = 1
        } else {
            i.genotype[pos] = 0
        }
        // actualizar el fenotipo y fitness
        //i = new Individual(i.getGenotype());
        i.update()
    }

    fun evalueMutate(probMutate: Double): Boolean {
        return Math.random() <= probMutate
    }

}