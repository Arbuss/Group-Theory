package field

open class IIntegerFromMultiplicativeFieldImpl(override val edge: Int, initNumber: Int) : IIntegerFromMultiplicativeField {
    override val zero = 0

    override var number: Int = initNumber % edge
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

    override fun minus(other: IIntegerFromMultiplicativeField): IIntegerFromMultiplicativeField {
        val result = copy()
        for(count in 0 until other.number) {
            result.decrement()
        }
        return result
    }

    override fun plus(other: IIntegerFromMultiplicativeField): IIntegerFromMultiplicativeField {
        val result = copy()
        for(count in 0 until other.number) {
            result.increment()
        }
        return result
    }

    override fun times(other: IIntegerFromMultiplicativeField): IIntegerFromMultiplicativeField {
        val result = IIntegerFromMultiplicativeFieldImpl(edge, 0)
        for(count in 0 until other.number * number) {
            result.increment()
        }
        return result
    }

    override fun div(other: IIntegerFromMultiplicativeField): IIntegerFromMultiplicativeField {
        val result = IIntegerFromMultiplicativeFieldImpl(edge, 1)

        while(result.canIncrement) {
            if(this == other * result) {
                return result
            } else {
                result.increment()
            }
        }
        throw Exception("не нашлось частного")
    }

    override fun unaryMinus(): IIntegerFromMultiplicativeField {
        return IIntegerFromMultiplicativeFieldImpl(edge, edge - number)
    }

    override fun equals(other: Any?): Boolean {
        (other as IIntegerFromMultiplicativeField)
        return number == other.number
    }
}