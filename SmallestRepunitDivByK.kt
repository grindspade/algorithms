

//11...1 will never devide by even number (K%2 == 0)
//11...1 will never device by mod 5/10 number (K%5==0)
// If 111..11 = K * n then it's devided 

fun smallestRepunitDivByK(K: Int): Int{
    if (K % 2 == 0 || K % 5 == 0){
        return -1
    }

    var v = 0
    for (i in 1..K){
        v = (10*v + 1) % K 
        if (v == 0){
            return i
        }
    }

    return -1
}


fun main (){
    if (smallestRepunitDivByK(1) == 1) println("OK") else println("Fail")
    if (smallestRepunitDivByK(2) == -1) println("OK") else println("Fail")
    if (smallestRepunitDivByK(3) == 3) println("OK") else println("Fail")
    println(smallestRepunitDivByK(7))
    

}