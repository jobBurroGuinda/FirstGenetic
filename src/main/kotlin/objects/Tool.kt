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
}