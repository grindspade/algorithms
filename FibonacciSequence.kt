
/*
 Top-down naive and simple
*/

fun fib_simple(n: Int, l: Int = 0): Int {  
    val v: Int

    if (n <= 1){
        v = 1 
    } else { 
        val t = " ".repeat(l)
        println ("$t fib($n)")
        v = fib_simple(n-1, l + 4) + fib_simple(n-2, l + 4)
    }
    return v
}


/*
    Top-down with memoization
    +fast O(n)
    -O(n) - memory
*/
fun fib_memoized(n: Int, l: Int, fn: MutableMap<Int,Int>): Int {  
    val v: Int

    if (n <= 1){
        v = 1 
    } else if (fn.containsKey(n)){
        v = fn[n] as Int
    } else { 
        val t = " ".repeat(l)
        println ("$t fib($n)")
        v = fib_memoized(n-1, l + 4, fn) + fib_memoized(n-2, l + 4, fn)
        fn[n] = v
    }

    return v
}


/*
    Bottom-up with boosting???
    +fast  O(n)
    +O(1) - memory

     We calculate the smaller values of fib first, then build larger values from them.
     Don't need recursion anymore, simple loop is ok
*/

fun fib_bottom_up(n: Int): Int {
    if (n == 0) return 0
    else {
        var prevFib = 0
        var curFib = 1
        for (i in 1..n){
            val newFib = prevFib + curFib
            prevFib = curFib
            curFib = newFib
        }
        return curFib
    }
}



fun main(){
    //val fn = mutableMapOf<Int, Int>()
    for (n in 0..10){
        println("fib($n) = ${fib_bottom_up(n)}")
        println("--------------------------------")
        //println("Climb Stair ($n) = ${climbStairs1(n)}")
    }
}