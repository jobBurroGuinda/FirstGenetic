package objects

import java.util.*

object Tool {
    // arreglos con datos binarios
    fun generateArray(n: Int): IntArray {
        val aux = IntArray(n)
        val r = Random()
        for (x in 0 until n) {
            aux[x] = r.nextInt(2)
        }
        return aux
    }

    fun checkGenotypeDecimal(array: IntArray):Boolean{
        var validate = false
        var j = array.size-1
        for(i in array.indices){
            //if(array[i])
        }

        return validate
    }

    fun isPair(n:Int):Boolean{
        return n % 2 == 0
    }

    fun generateRandomMask(n: Int): IntArray{
        return generateArray(n)
    }

    fun generateOnePointMask(n: Int){
        val aux = IntArray(n)
        if(isPair(n)){
            for(i in 0 until (n/2)){
                aux[i] = 0
            }
            for(i in (n/2) until n){
                aux[i] = 1
            }
        }
        else {
            for(i in 0 until ((n/2)-1)){
                aux[i] = 0
            }
            for(i in ((n/2)-1) until n){
                aux[i] = 1
            }
        }
    }

    fun generateTwoPointMask(n: Int){

    }

}