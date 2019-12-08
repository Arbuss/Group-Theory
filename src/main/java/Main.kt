import field.IIntegerFromMultiplicativeField
import matrix.IMatrix
import matrix.MatrixOverMultiplicativeField

typealias MatrixType = IMatrix<IIntegerFromMultiplicativeField>

fun getNextMatrix(prev: MatrixType) : MatrixType {
    val next = prev.copy()
    next.increment()
    return next
}

fun getGroup(matrixSize: Int, fieldEdge: Int): List<MatrixType> {
    val result = ArrayList<MatrixType>()
    var currentMatrix = MatrixOverMultiplicativeField(matrixSize, fieldEdge)
    result.add(currentMatrix)
    while(currentMatrix.canIncrement) {
        currentMatrix = getNextMatrix(currentMatrix) as MatrixOverMultiplicativeField
        result.add(currentMatrix)
    }
    return result
}

fun printMatrix(matrix: MatrixType) {
    for(j in 0 until matrix.size) {
        for (i in 0 until matrix.size){
            print(matrix.get(j, i).number)
        }
    }
    println()
}

fun easyGroupTest(matrixSize: Int, fieldEdge: Int) {
    val easyGroup = getGroup(matrixSize, fieldEdge)

//    easyGroup.forEach {
//        printMatrix(it)
//    }
    print(easyGroup.size)
}

fun shitIncrementMatrixTest() { // Перепутал строки и столбцы в printMatrix
    val shitIncrementMatrix = MatrixOverMultiplicativeField(2, 3).also {
        it.data[0].number = 0
        it.data[1].number = 0
        it.data[2].number = 0
        it.data[3].number = 2
    }
    printMatrix(shitIncrementMatrix)
    val incrementalMatrix = getNextMatrix(shitIncrementMatrix)
    printMatrix(incrementalMatrix)
}

fun lastMatrixIncrementFix() { // Не добавлялся последний элемент в результирующий список
    val shitIncrementMatrix = MatrixOverMultiplicativeField(2, 3).also {
        it.data[0].number = 2
        it.data[1].number = 2
        it.data[2].number = 2
        it.data[3].number = 1
    }
    printMatrix(shitIncrementMatrix)
    println(shitIncrementMatrix.canIncrement)
    val incrementalMatrix = getNextMatrix(shitIncrementMatrix)
    println(incrementalMatrix.canIncrement)
    printMatrix(incrementalMatrix)
}

fun main() {
    easyGroupTest(2, 79)
//    shitIncrementMatrixTest()
//    lastMatrixIncrementFix()
}