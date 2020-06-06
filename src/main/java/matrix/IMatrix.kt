package matrix

import field.IIntegerFromMultiplicativeField

interface IMatrix<T> {
    val size: Int
    val data: Array<T>
    val canIncrement: Boolean
    var determinant: IIntegerFromMultiplicativeField?

    operator fun set(row: Int, col: Int, element: T) {
        data[row * size + col] = element
    }

    fun get(row: Int, col: Int) = data[row * size + col]

    fun increment()

    fun copy(): IMatrix<T>

    fun getInvertible(): IMatrix<T>

    operator fun times(other: IMatrix<T>): IMatrix<T>
}