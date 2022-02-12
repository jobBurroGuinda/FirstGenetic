package objects

import java.util.*

class Tool {
    // arreglos con datos binarios
    companion object {
        fun generateArray(n: Int): IntArray {
            val aux = IntArray(n)
            val r = Random()
            for (x in 0 until n) {
                aux[x] = r.nextInt(2)
            }
            return aux
        }
    }
}