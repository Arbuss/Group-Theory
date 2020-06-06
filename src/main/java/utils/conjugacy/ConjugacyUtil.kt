package utils.conjugacy

import field.IIntegerFromMultiplicativeField
import matrix.IMatrix
import utils.matrix.MatrixUtil

typealias ConjugacyClass<T> = ArrayList<IMatrix<T>>

class ConjugacyUtil {
    companion object {
        fun <T> getConjugacyClasses(group: List<IMatrix<T>>): ArrayList<ConjugacyClass<T>> {
            val tempGroup = group.toMutableList()
            val conjugacyClasses = ArrayList<ConjugacyClass<T>>()

            //a~b : hah^-1 = b
            var classesCount = 0
            while (tempGroup.isNotEmpty()) {
                conjugacyClasses.add(arrayListOf())
                val conjugacyClass = getClass(group, tempGroup.first())
                conjugacyClasses[classesCount].addAll(conjugacyClass)
                tempGroup.removeAll(conjugacyClass)
                classesCount++
            }
            printClasses(conjugacyClasses)
            return conjugacyClasses
        }

        fun<T> printClasses(classes: ArrayList<ConjugacyClass<T>>) {
            for(conjugacyClass in classes) {
                printClass(conjugacyClass)
            }
        }

        private fun<T> printClass(conjugacyClass: ConjugacyClass<T>) {
            val lineLength = conjugacyClass.size * 11 - 1
            printLine(lineLength)
            MatrixUtil.printMatrix(conjugacyClass)
            printLine(lineLength)
        }

        private fun printLine(length: Int) {
            for(i in 0 until length) {
                print("-")
            }
            println()
        }

        private fun<T> getClass(group: List<IMatrix<T>>, lvalue: IMatrix<T>): ConjugacyClass<T> {
            val conjugacyClass = ConjugacyClass<T>()
            for(h in group) {
                val supposedB = h * lvalue * h.getInvertible()
                if(!conjugacyClass.contains(supposedB)) {
                    conjugacyClass.add(supposedB)
                }
            }
            return conjugacyClass
        }
    }
}