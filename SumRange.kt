/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3


1. You may assume that the array does not change.
2. There are many calls to sumRange function.

Because of 1 we may cache data. Because of 2 we have to cache data

*/


/*
    Naive version , calculate every time , bad O(n2) in worst cases n for calculate 
    from 0 to n and do it n times ->>> O(n2)
*/

fun sumRange(i: Int, j: Int, nums: List<Int>): Int{
    var s = 0
    for (x in  i..j){
        s += nums[x]
    }
    return s
}


//Create sums cache
fun createSumCache(nums: List<Int>): List<Int>{
    val n = nums.size
    val sums =  MutableList<Int>(n + 1){0}
    //println ("sums = $sums")
    for (i in 0 until n){
        sums[i + 1] = sums[i] + nums[i]
        //println ("$i: sums = $sums")
    }
    return sums
}

//Condition says i <= j, so we dont care about verification

fun sumRangeOptimazed(i: Int, j: Int, sumsCache: List<Int>) = sumsCache[j + 1] - sumsCache[i]



fun test(v: Boolean, desc: String = ""){

    if (desc.isNotEmpty()){
        print("$desc - ")
    }

    if (v){
        println("Successful")
    } else {
        println("Failed")
    }
}



fun main(){
    val nums = listOf(-2, 0, 3, -5, 2, -1)
    println ("nums = $nums")

    println("Calcualte in naive manner")
    test(sumRange(0,2, nums) == 1, "sumRange(0,2)")
    test(sumRange(2,5, nums) == -1, "sumRange(2,5)")
    test(sumRange(0,5, nums) == -3, "sumRange(0,5)")

    val sums = createSumCache(nums)
    println ("sums cache = $sums")

    println("Calcualte with cache or memoization")
    
    test(sumRangeOptimazed(0,2, sums) == 1, "sumRange(0,2)")
    test(sumRangeOptimazed(2,5, sums) == -1, "sumRange(2,5)")
    test(sumRangeOptimazed(0,5, sums) == -3, "sumRange(0,5)")

}