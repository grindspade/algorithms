

object Solution {

fun surfaceArea(grid: List<List<Int>>): Int{
    val n = grid.size
    var area = 0
    for (i in 0 until n){

        for (j in 0 until n){
            //Surface area as a single area
            val h = grid[i][j]
            if (h != 0){
                //Total area of the element
                area += h * 4 + 2   

                //Remove neighbor duplications
                if (i != 0){
                    area -= minOf(grid[i][j], grid[i-1][j])*2
                }
                if (j != 0){
                    area -= minOf(grid[i][j], grid[i][j-1])*2
                }
            }
        }
    }
    
    println(area)
    return area
}

fun test(b: Boolean, s: String){
    if (b){
        println("$s is OK!")
    } else {
        println("$s is FAIL!")
    }
}

}


fun runTest(name: String, block: ()->Unit){
    val header = "Starting tests $name--------------------"
    println(header)

    try {
        block()
    } catch (e: Exception){
        println("Something happens: ${e.localizedMessage}")
    }

    println("-".repeat(header.length))
}

fun main(){
    runTest("Surface Area of 3D shapes") {
        var grid = listOf(listOf(2))
        //Solution.test(Solution.surfaceArea(grid) == 10, "Grid ($grid) surface must be 10")

        grid = listOf(listOf(1,2),listOf(3,4))
        Solution.test(Solution.surfaceArea(grid) == 34, "Grid ($grid) surface must be 34")

        grid = listOf(listOf(1,0),listOf(0,2))
        Solution.test(Solution.surfaceArea(grid) == 16, "Grid ($grid) surface must be 16")

        grid = listOf(listOf(1,1,1),listOf(1,0,1),listOf(1,1,1))
        Solution.test(Solution.surfaceArea(grid) == 32, "Grid ($grid) surface must be 32")

        grid = listOf(listOf(2,2,2),listOf(2,1,2),listOf(2,2,2))
        Solution.test(Solution.surfaceArea(grid) == 46, "Grid ($grid) surface must be 46")
    }
}