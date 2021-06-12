
data class Point(val x: Int, val y: Int)


fun closest2Points(points: List<Point>, k: Int): List<Point>{
    
    var minPos1 = -1
    var minPos2 = -1
    var minDis1 = -1
    var minDis2 = -1

    for (i in 0 until points.size){
         val dis = points[i].x*points[i].x + points[i].y*points[i].y
         if (dis < minDis1){
             minDis2 = minDis1
             minDis1 = dis
             minPos2 = minPos1
             minPos1 = i
         } else if (dis < minDis2){
             minDis2 = dis
             minPos2 = i
         }
    }

    return listOf(points[minPos1], points[minPos2])
}


fun kClosest(points: List<Point>, k: Int): List<Point>{ 
    val distances = points.mapIndexed { index, point -> Pair(point.x*point.x + point.y * point.y,index) }.toMutableList()
    println(distances)
    distances.sortBy { it.first }
    println(distances)
    return distances.subList(0, k).map { points[it.second]}
}


fun main(){

    println(kClosest(listOf(Point(1,3), Point(-2,2)), 1))
    println(kClosest(listOf(Point(3,3), Point(5,-1), Point(-2,4)), 2))

}