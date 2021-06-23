
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


/* 
 * Above, we had just a value of best price but we don't have sizes of new roads.
 */
fun cutRodBottomUpFullSolution(p: List<Int>, n: Int): Pair<List<Int>, List<Int>>{
    
    //Do not initialize whole array because we start with one element only
    val r = MutableList(n+1){0}
    r[0] = 0

    val s = MutableList(n+1){0}

    for (j in 1..n){
        var q = Int.MIN_VALUE
        for (i in 1..j){    
            val v = p[i] + r[j-i]
            if (q < v){
                q = v
                s[j] = i
            } 
        }
        r[j] = q
    }
    return Pair(r,s)
}

fun printCutRodBottomUpFullSolution(p: List<Int>, n: Int){
    val (r,s) = cutRodBottomUpFullSolution(p, n)
    println ("Cutting Rod size $n")
    println ("r: $r")
    println ("s: $s")

    var size = n
    while (size > 0){
        print("${s[size]} ")
        size -= s[size]
    }
    println("")
}


/**
 * Cut rods using greedy strategy with density
 * 
 */

 /* 
 * Above, we had just a value of best price but we don't have sizes of new roads.
 */
fun cutRodBottomUpFullSolutionWithDensity(p: List<Float>, n: Int): Pair<List<Float>, List<Int>>{
    
    //Do not initialize whole array because we start with one element only
    val r = MutableList<Float>(n+1){0f}
    r[0] = 0f

    val s = MutableList(n+1){0}

    for (j in 1..n){
        var q = Int.MIN_VALUE.toFloat()
        for (i in 1..j){    
            val v = p[i] + r[j-i]
            if (q < v){
                q = v
                s[j] = i
            } 
        }
        r[j] = q
    }
    return Pair(r,s)
}


fun printCutRodBottomUpFullSolutionWithDensity(density: List<Float>, n: Int){
    val (r,s) = cutRodBottomUpFullSolutionWithDensity(density, n)

    println ("Greedy cutting by density")
    println ("r: $r")
    println ("s: $s")

    var size = n
    while (size > 0){
        print("${s[size]} ")
        size -= s[size]
    }
    println("")
}





/* 
 * Above, we had just a value of best price but we don't have sizes of new roads.
 * Here, each cutting has it's own cost - c
 */
fun cutRodBottomUpFullSolutionWithCutPrice(p: List<Int>, n: Int, c: Int): Pair<List<Int>, List<Int>>{
    
    //Do not initialize whole array because we start with one element only
    val r = MutableList(n+1){0}
    r[0] = 0

    val s = MutableList(n+1){0}  //Keep list of sub-rode sizes

    for (j in 1..n){
        var q = Int.MIN_VALUE
        for (i in 1..j){    
            val v = p[i] + r[j-i] -c
            if (q < v){
                q = v
                s[j] = i
            } 
        }
        r[j] = q
    }
    return Pair(r,s)
}


fun printCutRodBottomUpFullSolutionWithCutPrice(p: List<Int>, n: Int, c: Int){
    val (r,s) = cutRodBottomUpFullSolutionWithCutPrice(p, n, c)
    println ("Cutting Rod size $n with cutting price $c")
    println ("c: $c")
    println ("r: $r")
    println ("s: $s")

    var size = n
    while (size > 0){
        print("${s[size]} ")
        size -= s[size]
    }
    println("")
}



fun main(){

    //             0  1  2  3  4  5   6   7   8   9   10 
    val p = listOf(0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30)
    println ("prices: $p")

    //With densities we get them and they are not following same tendencies and prices
    //So Selection of parts could be another
    val density = p.mapIndexed { i, v -> if (v == 0) 0f else (v.toFloat())/(i.toFloat()) }
    println ("density: $density")

    val n = 10
    val q = cutRodBottomUp(p, n)
    println("Max price for rod size $n is $q")
    println("And now present a full solution")
    printCutRodBottomUpFullSolution(p, n)


    //This output proves that cutting is different
    printCutRodBottomUpFullSolutionWithDensity(density, n)


    printCutRodBottomUpFullSolutionWithCutPrice(p,n,1)


    val n1 = 7
    val q1 = cutRodBottomUp(p, n1)
    println("Max price for rod size $n1 is $q1")
    println("And now present a full solution")
    printCutRodBottomUpFullSolution(p, n1)

    //This output proves that cutting is different
    printCutRodBottomUpFullSolutionWithDensity(density, n1)

    printCutRodBottomUpFullSolutionWithCutPrice(p,n1,1)


    // println("Max prices [$r]")
    // println("Max first part sizes [$s]")


}