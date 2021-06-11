/*
Given two arrays, write a function to compute their intersection.

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

*/

fun intersectionSimple(nums1: List<Int>, nums2: List<Int>): List<Int>{
    
    val r = mutableListOf<Int>()

    for (num1 in nums1){
        if (!r.contains(num1)){
            if (nums2.contains(num1)){
                r.add(num1)
            }
        }
    }

    return r
}


fun intersectionWithHashedNums1(nums1: List<Int>, nums2: List<Int>): List<Int>{
    
    val hash = hashMapOf<Int,Int>()

    nums1.forEach { num -> 
        hash[num] = 1    
    }

    val r = mutableListOf<Int>()

    nums2.forEach{ num ->
        if ( hash.containsKey(num) && hash[num] != 0){
            r.add(num)
            hash[num] = 0
        } 
    }

    return r
}


fun intersectionWithSetNum1(nums1: List<Int>, nums2: List<Int>): List<Int>{
    
    val s: MutableSet<Int> = nums1.toMutableSet()
    val r = mutableListOf<Int>()

    nums2.forEach{ num ->
        if (s.remove(num)){
            r.add(num)
        }
    }

    return r
}



fun intersectionWithSorting(nums1: List<Int>, nums2: List<Int>): List<Int>{
    
    val nums1Sorted = nums1.sorted()
    val nums2Sorted = nums2.sorted()

    val r = mutableListOf<Int>()

    var i = 0
    var j = 0

    while (i < nums1Sorted.size && j < nums2Sorted.size){
        if (nums1Sorted[i] < nums2Sorted[j]){
            ++i
        } else if (nums1Sorted[i] > nums2Sorted[j]){
            ++j
        } else {
            val v = nums1Sorted[i]
            r.add(v)
            while (i < nums1Sorted.size && nums1Sorted[i] == v){
                ++i
            }
            while (j < nums2Sorted.size && nums2Sorted[i] == v){
                ++j
            }
        }
    }

    return r
}


fun main(){
    val nums11 = listOf(1,2,2,1)
    val nums21 = listOf(2,2)
    
    println("nums11: $nums11")
    println("nums21: $nums21")

    val r1 = intersectionWithSorting(nums11, nums21)

    println("r: $r1")

    val nums12 = listOf(4,9,5)
    val nums22 = listOf(9,4,9,8,4)
    
    println("nums12: $nums12")
    println("nums22: $nums22")

    val r2 = intersectionWithSorting(nums12, nums22)

    println("r2: $r2")
}