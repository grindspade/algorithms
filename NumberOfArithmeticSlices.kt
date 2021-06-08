/*
A sequence of number is called arithmetic if it consists of at least 
three elements and if the difference between any two consecutive elements is the same.
*/

fun numberOfArithmeticSlices(A: List<Int>): Int{
    
    var num = 0
    var count = 0
    val n = A.size

    for (i in 0..(n - 3)){
        if (A[i+1] - A[i] == A[i+2] - A[i+1]){
            ++count
            num += count
        } else {
            count = 0 
        }
    }
    return num
}


fun main(){
    val A = listOf(1,2,3,4)
    val r = numberOfArithmeticSlices(A)

    println("A = $A")
    println("numberOfArithmeticSlices(A) = $r")
}