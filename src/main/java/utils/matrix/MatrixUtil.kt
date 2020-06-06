package utils.matrix

import field.IIntegerFromMultiplicativeField
import matrix.IMatrix

class MatrixUtil {
    companion object {
        fun<T> printMatrix(matrix: IMatrix<T>) {
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
                print(" = ${matrix.determinant!!.number} | ")
            }
            println()
        }
    }
}