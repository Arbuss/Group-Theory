package matrix

import field.IIntegerFromMultiplicativeField
import field.IntegerFromMultiplicativeField

class MatrixOverMultiplicativeField(override val size: Int, edgeField: Int) : IMatrix<IIntegerFromMultiplicativeField> {
    override var data: Array<IIntegerFromMultiplicativeField> = Array(size * size) { IntegerFromMultiplicativeField(edgeField, 0) }
    override val canIncrement: Boolean
        get() = canIncrement()

    override fun increment() {
        val reverseData = data.reversedArray()
        var incrementNext = reverseData[0].increment() == 0
        for(i in 1 until reverseData.size) {
            if(incrementNext) {
                incrementNext = reverseData[i].increment() == 0
            }
        }
        data = reverseData.reversedArray()
    }

    override fun copy(): IMatrix<IIntegerFromMultiplicativeField> {
        val copy = MatrixOverMultiplicativeField(size, data[0].edge)
        for(i in copy.data.indices) {
            copy.data[i] = this.data[i].copy()
        }
        return copy
    }

    private fun canIncrement(): Boolean {
        data.forEach {
            if(it.canIncrement) {
                return true
            }
        }
        return false
    }
}