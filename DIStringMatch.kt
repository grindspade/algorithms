

fun diStringMatch(S: String): List<Int>{
    var r = mutableListOf<Int>()    
    var lo = 0
    var hi = S.length

    S.forEach { r.add(if (it == 'I') lo++ else hi--) }
    r.add(lo)
    println ("$S->$r")
    return r.toList()
}


fun main(){
    diStringMatch("IDID")
    diStringMatch("III")
    diStringMatch("DDI")
}