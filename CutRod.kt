
/*
    Top - down approach - naive recursion

    exponet approach - by-pass whole tree of variants
*/
fun cutRod(p: List<Int>, n: Int): Int{
    if (n == 0) return 0
    var q = Int.MIN_VALUE
    for (i in 1..n){
        q = maxOf(q, p[i] + cutRod(p, n - i)) 
    }
    return q
}

/*
Now lets use dynamic programming approach as top-down approach with memoization
*/
fun cutRodMemoized(p: List<Int>, n: Int): Int{
    val r = MutableList(n+1){Int.MIN_VALUE}
    return cutRodMemoizedAux(p, n, r)
}

fun cutRodMemoizedAux(p: List<Int>, n: Int, r: MutableList<Int>): Int{
    if (r[n] >= 0) return r[n]
    var q = Int.MIN_VALUE
    if (n == 0) q = 0
    else {
        for (i in 1..n){
            q = maxOf(q, p[i] + cutRodMemoizedAux(p, n - i,r)) 
        }
        r[n] = q
    }
    return q
}

/*
Now lets use dynamic programming approach as bottom-up approach
Calculate small sub-problems first and then get next ones
*/

fun cutRodBottomUp(p: List<Int>, n: Int): Int{
    
    //Do not initialize whole array because we start with one element only
    val r = MutableList(n+1){0}
    r[0] = 0

    for (j in 1..n){
        var q = 0
        for (i in 1..j){    
            q = maxOf(q, p[i] + r[j-i]) 
        }
        r[j] = q
    }
    return r[n]
}


fun main(){

    val p = listOf(0, 1, 5, 8, 9)

    val n = 4
    val q = cutRodBottomUp(p, n)

    println("Max price for rod size $n is $q")

}