package field

interface IIntegerFromMultiplicativeField {
    var number: Int
    val canIncrement: Boolean
    val edge: Int

    fun increment(): Int
    fun decrement(): Int
    fun copy(): IIntegerFromMultiplicativeField
}