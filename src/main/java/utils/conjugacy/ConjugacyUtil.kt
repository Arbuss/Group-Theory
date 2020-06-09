package utils.conjugacy

import field.IIntegerFromMultiplicativeField
import matrix.IMatrix
import utils.matrix.MatrixUtil

typealias ConjugacyClass<T> = ArrayList<IMatrix<T>>
typealias ConjugacyClassWithTriplets<T> = ArrayList<Triple<IMatrix<T>, IMatrix<T>, IMatrix<T>>>

class ConjugacyUtil {
    companion object {
        fun <T> getConjugacyClasses(group: List<IMatrix<T>>): ArrayList<ConjugacyClass<T>> {
            val tempGroup = group.toMutableList()
            val conjugacyClasses = ArrayList<ConjugacyClass<T>>()
            val conjugacyClassesWithTriplets = ArrayList<ConjugacyClassWithTriplets<T>>()

            //a~b : hah^-1 = b
            var classesCount = 0
            while (tempGroup.isNotEmpty()) {
                conjugacyClasses.add(arrayListOf())
                conjugacyClassesWithTriplets.add(arrayListOf())
                val conjugacyClass = getClass(group, tempGroup.first())

                conjugacyClasses[classesCount].addAll(conjugacyClass.first)
                conjugacyClassesWithTriplets[classesCount].addAll(conjugacyClass.second)

                tempGroup.removeAll(conjugacyClass.first)
                classesCount++
            }
//            for(triplet in conjugacyClassesWithTriplets) {
//                MatrixUtil.printMatrixTripletLaTeX(triplet)
//            }
            printClasses(conjugacyClasses)
            return conjugacyClasses
        }

        fun<T> printClasses(classes: ArrayList<ConjugacyClass<T>>) {
            for(conjugacyClass in classes) {
                printClass(conjugacyClass)
            }
        }

        private fun<T> printClass(conjugacyClass: ConjugacyClass<T>) {
//            val lineLength = conjugacyClass.size * 6 - 1
//            printLine(lineLength)
            println("\\item")
            MatrixUtil.printMatrixLaTeX(conjugacyClass)
//            printLine(lineLength)
        }

        private fun printLine(length: Int) {
            for(i in 0 until length) {
                print("-")
            }
            println()
        }

        private fun<T> getClass(group: List<IMatrix<T>>, lvalue: IMatrix<T>)
                : Pair<ConjugacyClass<T>, ConjugacyClassWithTriplets<T>> {
            val conjugacyClass = ConjugacyClass<T>()
            val conjugacyClassWithTriplets = ConjugacyClassWithTriplets<T>()
            for(h in group) {
                val supposedB = h * lvalue * h.getInvertible()
                if(!conjugacyClass.contains(supposedB)) {
                    conjugacyClass.add(supposedB)

                    if(lvalue != supposedB) {
                        conjugacyClassWithTriplets.add(Triple(lvalue, supposedB, h))
                    }
                }
            }
            return Pair(conjugacyClass, conjugacyClassWithTriplets)
        }
    }
}