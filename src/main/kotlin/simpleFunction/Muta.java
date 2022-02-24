package simpleFunction;

import objects.Individual;

import java.util.Random;

public class Muta {

    public static void muta(Individual i){
        //int[] gen = i.getGenotype().clone();
        // necesitamos una posición para poder modificar
        Random ran = new Random();
        int pos = ran.nextInt(i.getGenotype().length);
        // Se modifica el valor del fenotipo
        if(i.getGenotype()[pos] == 0) {
            i.getGenotype()[pos] = 1;
        } else {
            i.getGenotype()[pos] = 0;
        }
        // actualizar el fenotipo y fitness
        //i = new Individual(i.getGenotype());
        i.update();
    }

}
