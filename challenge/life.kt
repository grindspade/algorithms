//import kotlin.random.Random
import java.security.SecureRandom


typealias IntMatrixRow = IntArray
typealias IntMatrix = Array<IntMatrixRow> 




class Life(val rows: Int, val cols: Int){



val grid0 = IntMatrix(rows){ IntMatrixRow(cols){0}}
val grid1 = IntMatrix(rows){ IntMatrixRow(cols){0}}

var grid = grid0
var newGrid = grid1

var generation = 1

init {
    populate()
}



fun clear(){
    grid = grid0
    newGrid = grid1
    
    generation = 1

    for (row in 0 until rows){
        for (col in 0 until cols){
            grid[row][col] =  0
        }
    }
}


fun reinit(){
    grid = grid0
    newGrid = grid1
    generation = 1
    populate()
}



fun populate(){
    val secureRandom  = SecureRandom()
    for (row in 0 until rows){
        for (col in 0 until cols){
            grid[row][col] =  secureRandom.nextInt(2)
        }
    }
}

// fun populateForTest(){

//     val x = arrayOf(
//         intArrayOf(0,0,0,0,0),
//         intArrayOf(0,0,1,0,0),
//         intArrayOf(0,0,1,0,0),
//         intArrayOf(0,0,1,0,0),
//         intArrayOf(0,0,0,0,0),
//     )

//     val secureRandom  = SecureRandom()
//     for (row in 0 until rows){
//         for (col in 0 until cols){
//             grid[row][col] =  x[row][col]
//         }
//     }
// }
 


private fun getNeighbors(row: Int, col: Int): Int{
    var sum = 0
    if (row > 0){
        if (col > 0){
            sum += grid[row-1][col-1]    
        }
        if (col < cols - 1){
            sum += grid[row-1][col+1]    
        }
        sum += grid[row-1][col]
    }          
    if (row < rows - 1){
        if (col > 0){
            sum += grid[row+1][col-1]    
        }
        if (col < cols - 1){
            sum += grid[row+1][col+1]    
        }
        sum += grid[row+1][col]
    }
    if (col > 0){
        sum += grid[row][col-1]
    }
    if (col < cols - 1){
        sum += grid[row][col+1]    
    }
    return sum
}

fun next(): Boolean{
        var total = 0
        for (row in 0 until rows){
            for (col in 0 until cols){
                val cell :Int = grid[row][col]
                total += cell
                var sum = getNeighbors(row, col)

                newGrid[row][col] = when (cell){
                     0 -> {
                        when (sum){
                             3 -> 1
                             else -> 0
                         }
                     }
                     else -> {
                        when (sum){
                             2,3 -> 1
                             else -> 0
                         }
                     }
                }               
            } //for col
        }//For row

        run { val t = grid; grid = newGrid; newGrid = t}

        return if (total > 0){
            ++generation
            true
        } else false
}


}


enum class OS {
    WINDOWS, LINUX, MAC
}

class LifePresenter(val life: Life, val delay: Long){
    companion object {
        const val EMPTY_CELL = " "
        const val LIVE_CELL =  "\u2588"
    }

    fun print(){
        println("Generation: ${life.generation}")
        
        life.grid.forEachIndexed{ idx, row ->  
            print("${idx}: ")
            row.forEach { cell -> 
                if (cell != 0) print(LIVE_CELL) else print(EMPTY_CELL)
            }
            println()
        }
    }


    fun go(){
        while (true){
            val ok = life.next()
            cls()
            print()
            if (!ok) break
            Thread.sleep(delay)
        }
    }



    private fun getOS(): OS?{
        val os = System.getProperty("os.name").toLowerCase()

        return when {
            os.contains("win")  -> {
                OS.WINDOWS
            }
            os.contains("nix") || os.contains("nux") || os.contains("aix")-> {
                OS.LINUX
            }
            os.contains("mac") -> {
                OS.MAC
            } else -> null
        }
    }


    fun cls(){
        when(getOS()){
            OS.WINDOWS -> {
                //Runtime.getRuntime().exec("cls")
                ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor()
            }
            else -> Runtime.getRuntime().exec("clear")
        }   
    }
}

fun main(args: Array<String>){

    val width = try { args[0].toInt() } catch(e: Exception) {40}
    val height = try { args[1].toInt() } catch(e: Exception) {20}
    val delay = try { args[2].toLong() } catch(e: Exception) {1_000L}


    val life = Life(height, width)

    val presenter = LifePresenter(life, delay)

    // presenter.print()
    // Thread.sleep(2000)
    // presenter.cls()
    // Thread.sleep(2000)
    // presenter.print()
    


    presenter.go()

}