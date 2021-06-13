import java.util.LinkedList


fun printOrangesGrid(grid: List<List<Int>>){
    
    val length = (grid[0].size+2)

    println("-".repeat(length))
    for (i in 0 until grid.size){
        print("|")
        for (j in 0 until grid[i].size){
            when(grid[i][j]){
                0 -> print(" ")
                1 -> print("O")
                2 -> print("X")
                3 -> print("+")
            }
        }
        println("|")
    }
    println("-".repeat(length))
    println("")
}


fun orangesRotting(grid: MutableList<MutableList<Int>>): Int{
    printOrangesGrid(grid)

    var steps = 0
    var fresh = 0
    val rotten = LinkedList<Pair<Int,Int>>()

    for (i in 0 until grid.size){
        for (j in 0 until grid[i].size){
            when(grid[i][j]){
                1 -> fresh++
                2 -> rotten.add(Pair(i,j))
            }
        }
    }


    println(fresh)
    println(rotten)
    

    val dirs = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

    while(rotten.isNotEmpty()) {
        var n = rotten.size
        var rott = false

        repeat(n){
            val p = rotten.pop()
            if (p != null){
                val y = p.first
                val x = p.second

                dirs.forEach{ dir -> 
                    val i = y + dir.first
                    val j = x + dir.second
                    if (i >= 0 && i < grid.size && j >= 0 && j < grid[i].size && grid[i][j] == 1){
                        grid[i][j] = 2
                        rotten.add(Pair(i,j))
                        --fresh
                        rott = true
                    }
                }
            }
        }

        if (rott){
            ++steps
        }

        printOrangesGrid(grid)
        println(fresh)
        println(rotten)
    }

    return if (fresh != 0) -1 else steps
}


fun main(){

    val grid = mutableListOf(
        mutableListOf(2,1,1),
        mutableListOf(1,1,0),
        mutableListOf(0,1,1) 
    )
    val r = 4
    if (orangesRotting(grid) == r) println("OK")

    val grid1 = mutableListOf(
        mutableListOf(2,1,1),
        mutableListOf(0,1,1),
        mutableListOf(1,0,1) 
    )
    val r1 = -1
    if (orangesRotting(grid1) == r1) println("OK")

    val grid2 = mutableListOf(
        mutableListOf(0,2)
    )
    val r2 = 0
    if (orangesRotting(grid2) == r2) println("OK") 
}