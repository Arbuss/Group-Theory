package utils.group

import field.IIntegerFromMultiplicativeField
import matrix.IMatrix
import matrix.MatrixOverMultiplicativeField

typealias MatrixType = IMatrix<IIntegerFromMultiplicativeField>

class GroupUtil {
    companion object {
        fun getGroup(matrixSize: Int, fieldEdge: Int): List<MatrixType> {
            val result = ArrayList<MatrixType>()
            var currentMatrix = MatrixOverMultiplicativeField(matrixSize, fieldEdge)
            result.add(currentMatrix)
            while(currentMatrix.canIncrement) {
                currentMatrix = getNextMatrix(currentMatrix) as MatrixOverMultiplicativeField
                if(!result.contains(currentMatrix)) {
                    result.add(currentMatrix)
                }
            }
            return result
        }

        private fun getNextMatrix(prev: MatrixType) : MatrixType {
            val next = prev.copy()
            next.increment()
            return next
        }

        fun printMatrix(matrix: MatrixType) {
            matrix.data.forEach {
                print(it.number)
            }
            print(" = ${matrix.determinant!!.number}")
            println()
        }
    }
}