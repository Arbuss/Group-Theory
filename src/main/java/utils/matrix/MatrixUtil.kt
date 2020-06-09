package utils.matrix

import field.IIntegerFromMultiplicativeField
import matrix.IMatrix

typealias MatrixTriplet<T> = Triple<IMatrix<T>, IMatrix<T>, IMatrix<T>>

class MatrixUtil {
    companion object {
        fun<T> printMatrixTriplet(matrixTriplet: MatrixTriplet<T>) {
            printData(matrixTriplet.first.data)
            print(" ~ ")
            printData(matrixTriplet.second.data)
            print(" via ")
            printData(matrixTriplet.third.data)
            println()
        }

        fun<T> printMatrixTriplet(matrixTriplets: List<MatrixTriplet<T>>) {
            for(matrixTriplet in matrixTriplets) {
                printData(matrixTriplet.first.data)
                print(" ~ ")
                printData(matrixTriplet.second.data)
                print(" via ")
                printData(matrixTriplet.third.data)
                print("; ")
            }
            println()
        }

        fun<T> printMatrixTripletLaTeX(matrixTriplets: List<MatrixTriplet<T>>) {
            for(matrixTriplet in matrixTriplets) {
                printMatrixLaTeX(matrixTriplet.first)
                print(" и ")
                printMatrixLaTeX(matrixTriplet.second)
                print(" через ")
                printMatrixLaTeX(matrixTriplet.third)
                print("; ")
            }
            println()
        }

        //$\begin{pmatrix}0&1\\2&1 \end{pmatrix}$,
        private fun<T> printMatrixLaTeX(matrix: IMatrix<T>) {
            val first = (matrix.data[0] as IIntegerFromMultiplicativeField).number
            val second = (matrix.data[1] as IIntegerFromMultiplicativeField).number
            val third = (matrix.data[2] as IIntegerFromMultiplicativeField).number
            val fourth = (matrix.data[3] as IIntegerFromMultiplicativeField).number
            print("\$\\begin{pmatrix}$first&$second\\\\$third&$fourth \\end{pmatrix}\$")
        }

        private fun<T> printData(data: Array<T>) {
            data.forEach {
                it as IIntegerFromMultiplicativeField
                print(it.number)
            }
        }

        fun<T> printMatrix(matrix: IMatrix<T>) {
            matrix.data.forEach {
                it as IIntegerFromMultiplicativeField
                print(it.number)
            }
            println()
        }

        fun<T> printMatrixWithDeterminant(matrix: IMatrix<T>) {
            matrix.data.forEach {
                it as IIntegerFromMultiplicativeField
                print(it.number)
            }
            print(" = ${matrix.determinant!!.number}")
            println()
        }

        fun<T> printMatrix(matrixList: List<IMatrix<T>>) {
            matrixList.forEach { matrix->
                matrix.data.forEach {
                    it as IIntegerFromMultiplicativeField
                    print(it.number)
                }
                print("; ")
            }
            println()
        }

        fun<T> printMatrixLaTeX(matrixList: List<IMatrix<T>>) {
            matrixList.forEach { matrix->
                printMatrixLaTeX(matrix)
                print("; ")
            }
            println()
        }

        fun<T> printMatrixWithDeterminant(matrixList: List<IMatrix<T>>) {
            matrixList.forEach { matrix->
                matrix.data.forEach {
                    it as IIntegerFromMultiplicativeField
                    print(it.number)
                }
                print(" = ${matrix.determinant!!.number} | ")
            }
            println()
        }
    }
}