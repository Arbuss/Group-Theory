package field

interface IIntegerFromMultiplicativeField {
    val zero: Int
    var number: Int
    val canIncrement: Boolean
    val edge: Int

    fun increment(): Int
    fun decrement(): Int
    fun copy(): IIntegerFromMultiplicativeField
    operator fun times(other: IIntegerFromMultiplicativeField): IIntegerFromMultiplicativeField
    operator fun div(other: IIntegerFromMultiplicativeField): IIntegerFromMultiplicativeField
    operator fun minus(other: IIntegerFromMultiplicativeField): IIntegerFromMultiplicativeField
    operator fun plus(other: IIntegerFromMultiplicativeField): IIntegerFromMultiplicativeField
    operator fun unaryMinus(): IIntegerFromMultiplicativeField
}