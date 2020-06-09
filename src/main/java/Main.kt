import field.IIntegerFromMultiplicativeField
import utils.conjugacy.ConjugacyUtil
import utils.group.GroupUtil
import utils.group.MatrixType
import utils.matrix.MatrixUtil

fun easyGroupTest(matrixSize: Int, fieldEdge: Int) {
    val easyGroup = GroupUtil.getGroup(matrixSize, fieldEdge)

    var counter = 1
    easyGroup.forEach {
        print("${counter++}. ")
        MatrixUtil.printMatrix(it)
    }
    print(easyGroup.size)
}

//fun shitIncrementMatrixTest() { // Перепутал строки и столбцы в printMatrix
//    val shitIncrementMatrix = MatrixOverMultiplicativeField(2, 3).also {
//        it.data[0].number = 0
//        it.data[1].number = 0
//        it.data[2].number = 0
//        it.data[3].number = 2
//    }
//    GroupUtil.printMatrix(shitIncrementMatrix)
//    val incrementalMatrix = GroupUtil.getNextMatrix(shitIncrementMatrix)
//    GroupUtil.printMatrix(incrementalMatrix)
//}

//fun lastMatrixIncrementFix() { // Не добавлялся последний элемент в результирующий список
//    val shitIncrementMatrix = MatrixOverMultiplicativeField(2, 3).also {
//        it.data[0].number = 2
//        it.data[1].number = 2
//        it.data[2].number = 2
//        it.data[3].number = 1
//    }
//    printMatrix(shitIncrementMatrix)
//    println(shitIncrementMatrix.canIncrement)
//    val incrementalMatrix = GroupUtil.getNextMatrix(shitIncrementMatrix)
//    println(incrementalMatrix.canIncrement)
//    printMatrix(incrementalMatrix)
//}

fun getGroupSize(matrixSize: Int, fieldEdge: Int): Int {
    val easyGroup = GroupUtil.getGroup(matrixSize, fieldEdge)
    val groupWithNonNullDeterminants: ArrayList<MatrixType> = arrayListOf()
    val groupWithNullDeterminants: ArrayList<MatrixType> = arrayListOf()

    easyGroup.forEach {
        val det = it.determinant!!
        if (det.number != IIntegerFromMultiplicativeField.ZERO) {
            groupWithNonNullDeterminants.add(it)
        } else {
            groupWithNullDeterminants.add(it)
        }
    }

//    var counter = 1
//    groupWithNonNullDeterminants.forEach {
//        print("${counter++}. ")
//        printMatrix(it)
//    }

    groupWithNullDeterminants.forEach {
        MatrixUtil.printMatrix(it)
    }

    return groupWithNonNullDeterminants.size
}

fun calcFormula(coeffsList: List<Int>, p: Int): Int {
    var result = 1

    coeffsList.forEach {
        result *= p + it
    }

    return result
}

fun testFormula() {
    val primeNumbers = arrayOf(3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67)

    for (primeNumber in primeNumbers) {
        val size = getGroupSize(2, primeNumber)
        if (size == formula(primeNumber)) {
            println("p = $primeNumber; size = $size")
        } else {
            println("AW SHIT! HERE WE GO AGAIN")
        }
    }
}

fun formula(p: Int) = (p * p - 1) * (p * p - p)

fun main() {
    val matrixSize = 2
    val fieldEdge = 3
//    easyGroupTest(2, 2)
//    shitIncrementMatrixTest()
//    lastMatrixIncrementFix()
//    hardGroupTest(2, 3)
//    coefficientsSelection()
//    testFormula()
//    getGroupSize(2, 3)
    ConjugacyUtil.getConjugacyClasses(GroupUtil.getGroup(matrixSize, fieldEdge))
}