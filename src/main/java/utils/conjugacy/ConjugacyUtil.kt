package utils.conjugacy

import matrix.IMatrix
import utils.matrix.MatrixUtil

typealias ConjugacyClass<T> = List<IMatrix<T>>

class ConjugacyUtil {
    companion object {
        fun <T> getConjugacyClasses(group: List<IMatrix<T>>) {
            val conjugacyClasses = ArrayList<ConjugacyClass<T>>()
            val rawTriplets = ArrayList<Triple<IMatrix<T>, IMatrix<T>, IMatrix<T>>>()

            for(g1 in group) {
                for(g2 in group) {
                    if(g1 == g2) continue
                    for(h in group) {
                        val someMatrix = h * g1 * h.getInvertible()
                        if(g2 == someMatrix) {
                            rawTriplets.add(Triple(g1, g2, h))
                        }
                    }
                }
            }
            println(rawTriplets.size)
            rawTriplets.forEach {
                MatrixUtil.printMatrix(arrayListOf(it.first, it.second, it.third))
            }
        }
    }
}