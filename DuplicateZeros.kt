
fun duplicateZeros(nums: MutableList<Int>){
    var i = 0
    var e = nums.size - 1

    var shift = 0

    while (i <= e){
        if (nums[i] == 0){
            shift++
            --e
        }
        ++i
    }

    i = e
    var j = e + shift
    while (i >= 0 && shift != 0){
        nums[j--] = nums[i]
        if (nums[i] == 0){
            --shift
            nums[j--] = nums[i]
        }
        --i
    }
}


fun main (){
    var nums = mutableListOf(1,0,2,3,0,4,5,0)
    //val res = mutableListOf(1,0,0,2,3,0,0,4)
    println(nums)
    duplicateZeros(nums)
    println(nums) 


    nums = mutableListOf(1,2,3)
    println(nums)
    duplicateZeros(nums)
    println(nums) 
    
}