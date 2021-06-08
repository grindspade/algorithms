
// You are climbing a stair case. It takes n steps to reach to the top.
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
// 
//Note: Given n will be a positive integer.

/*
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/




fun climbStairs(n: Int): Int{
    var v: Int = 0

    fun climbStairs_(n: Int): Int {
        val ways = when (n){
            0 -> {
                ++v //One more way finished
                0   
            }
            1 -> {
                ++v
                1
            }
            else -> climbStairs_(n - 1) + climbStairs_(n - 2)
        }
        return ways
    }

    climbStairs_(n)

    return v
}


fun climbStairs1(n: Int): Int{
    //d[0] undef but lets keep 1
    //d[1] = 1
    //d[i] = d[i-1] + d[i-2]
    var prev2 = 1
    var prev1 = 1
    for (i in 2..n){
        val curr = prev2 + prev1
        prev2 = prev1
        prev1 = curr
    }

    return prev1
}





fun main(){

    for (n in 0..6){
        println("Climb Stair ($n) = ${climbStairs(n)}")
        println("Climb Stair ($n) = ${climbStairs1(n)}")
    }

}