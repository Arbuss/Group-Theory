package matrix

import field.IIntegerFromMultiplicativeField
import field.IntegerFromMultiplicativeField

class MatrixOverMultiplicativeField(override val size: Int, edgeField: Int) : IMatrix<IIntegerFromMultiplicativeField> {
    override var data: Array<IIntegerFromMultiplicativeField> = Array(size * size) { IntegerFromMultiplicativeField(edgeField, 0) }
    override val canIncrement: Boolean
        get() = canIncrement()
    override var determinant: IIntegerFromMultiplicativeField? = null
        get() = if (field == null) {
            calcDeterminant()
        } else {
            field
        }


    override fun increment() {
        val reverseData = data.reversedArray()
        var incrementNext = reverseData[0].increment() == 0
        for (i in 1 until reverseData.size) {
            if (incrementNext) {
                incrementNext = reverseData[i].increment() == 0
            }
        }
        data = reverseData.reversedArray()
    }

    override fun copy(): IMatrix<IIntegerFromMultiplicativeField> {
        val copy = MatrixOverMultiplicativeField(size, data[0].edge)
        for (i in copy.data.indices) {
            copy.data[i] = this.data[i].copy()
        }
        return copy
    }

    override fun getInvertible(): IMatrix<IIntegerFromMultiplicativeField> {
        val result = MatrixOverMultiplicativeField(size, data[0].edge)

        result.data[0] = data[3] / determinant!!
        result.data[1] = -data[1] / determinant!!
        result.data[2] = -data[2] / determinant!!
        result.data[3] = data[0] / determinant!!

        return result
    }

    override fun times(other: IMatrix<IIntegerFromMultiplicativeField>): IMatrix<IIntegerFromMultiplicativeField> {
        val result = MatrixOverMultiplicativeField(size, data[0].edge)

        result.data[0] = data[0] * other.data[0] + data[1] * other.data[2]
        result.data[1] = data[0] * other.data[1] + data[1] * other.data[3]
        result.data[2] = data[2] * other.data[0] + data[3] * other.data[2]
        result.data[3] = data[2] * other.data[1] + data[3] * other.data[3]

        return result
    }

    override fun equals(other: Any?): Boolean {
        (other as IMatrix<IIntegerFromMultiplicativeField>)
        for (index in 0 until size * size) {
            if (data[index] != other.data[index]) {
                return false
            }
        }
        return true
    }

    private fun canIncrement(): Boolean {
        data.forEach {
            if (it.canIncrement) {
                return true
            }
        }
        return false
    }

    private fun calcDeterminant(): IIntegerFromMultiplicativeField {
        return (data[0] * data[3] - data[1] * data[2])
    }
}