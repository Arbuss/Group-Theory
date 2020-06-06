package utils.conjugacy

import matrix.IMatrix

typealias ConjugacyClass<T> = List<IMatrix<T>>

class ConjugacyUtil {
    companion object {
        fun <T> getConjugacyClasses(group: List<IMatrix<T>>) {
            val conjugacyClasses = ArrayList<ConjugacyClass<T>>()
            val rawPairs = ArrayList<Triple<IMatrix<T>, IMatrix<T>, IMatrix<T>>>()

            for(g1 in group) {
                for(g2 in group) {
                    if(g1 == g2) continue
                    for(h in group) {
                        val someMatrix = h * g1 * h.getInvertible()
                        if(g2 == someMatrix) {
                            rawPairs.add(Triple(g1, g2, h))
                        }
                    }
                }
            }
        }
    }
}