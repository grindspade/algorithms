/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num 
calculate the number of 1's in their binary representation and return them as an array.

For num = 5 you should return [0,1,1,2,1,2].

It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
But can you do it in linear time O(n) /possibly in a single pass?

Space complexity should be O(n).

Can you do it like a boss? Do it without using any builtin function 
like __builtin_popcount in c++ or in any other language.

*/

/*
    Naive straightforward implementation
    works but - complexity is:
    Space -> O(n) - ok
    Speed -> O(n*log2(n))
*/

fun countBits(n: Int): List<Int>{

    val ans = MutableList(n+1){0}

    for (i in 0..n){
        var s = 0
        var num = i
        while (num > 0){
            val rem = num % 2
            if (rem > 0){
                ++s
            }
            num = num / 2
        }
        ans[i] = s 
    }

    return ans
}


/*
My variant, same complexity as optimal but no divides
*/

fun countBits1(n: Int): List<Int>{
    val ans = MutableList(n+1){0}

    //Used this variables to exclude dividing and bit shifting
    var prevStep = 0
    var step = 1

    for (i in 1..n){
        if (i == step){
            ans[i] = ans[i-step] + 1
            prevStep = step 
            step = step + step
        } else {
            ans[i] = ans[i-prevStep] + 1
        }
    } 

    return ans
}

/*
    Optimal But 2 devides per turn
*/

fun countBits2(n: Int): List<Int>{
    val ans = MutableList(n+1){0}

    for (i in 1..n){
        ans[i] = ans[i/2] + if (i%2 != 0) 1 else 0
    } 

    return ans
}


fun main(){
    val num = 16

    // val ans1 = countBits1(num)
    // println("$num : $ans1")


    val ans2 = countBits2(num)
    println("$num : $ans2")
    // ans.forEach {  
    //     println("====".repeat(it))
    // }
}