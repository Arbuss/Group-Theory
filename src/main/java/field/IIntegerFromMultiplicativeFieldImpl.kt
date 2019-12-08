package field

open class IIntegerFromMultiplicativeFieldImpl(override val edge: Int, initNumber: Int) : IIntegerFromMultiplicativeField {
    override var number: Int = initNumber
        set(value) {
            field = value % edge
        }

    override val canIncrement: Boolean
        get() = number + 1 != edge


    override fun increment(): Int {
        if (!canIncrement) {
            number = 0
        } else {
            number++
        }
        return number
    }

    override fun decrement(): Int {
        if (number == 0) {
            number = edge - 1
        } else {
            number--
        }
        return number
    }

    override fun copy() = IIntegerFromMultiplicativeFieldImpl(edge, number)
}