/*
Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings
even they consist of same characters.

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

The input string length won't exceed 1000.
*/

/*
Substring s[i..j] is palindromic if s[i] == s[j] and s[i+1..j-1] is palindrome

Number of PS(palindromic substrings) in s[0..i] is PS in s[0..i-1] + PSn(ending with S[i])

*/

fun countPalindromicSubstrings(s: String): Int{ 
    var num = 0
   
    for (i in 0 until s.length){
        num += countPalindromic(s, i, i)
        num += countPalindromic(s, i, i+1) 
    }

    return num
}


fun countPalindromic(s: String, left: Int, right: Int): Int{ 
    var count = 0
    var l = left
    var r = right
   
    while ( l >= 0 && r < s.length && s[l] == s[r]){
        ++count
        --l
        ++r
    }


    return count
}



//Toast company,
//Palindrome string with spaces and periods  
//

fun isPalindrome(str: String): Boolean {
    var l = 0
    var r = str.length - 1

    while (l < r){
        if (str[l] == ' ' || str[l] == '.'){
            ++l
        } else if (str[r] == ' ' || str[r] == '.'){
            --r
        } else if (str[l].toLowerCase() != str[r].toLowerCase()){
            return false    
        } else {
            ++l
            --r
        }
    }

    return true
}



fun main(){
    val s1 = "abc"
    val r1 = countPalindromicSubstrings(s1)
    println("countPalindromicSubstrings('${s1}') = $r1")

    val s2 = "aaa"
    val r2 = countPalindromicSubstrings(s2)
    println("countPalindromicSubstrings('${s2}') = $r2")

    val s3 = "aba"
    val r3 = countPalindromicSubstrings(s3)
    println("countPalindromicSubstrings('${s3}') = $r3")

    val s4 = "Able was I ere I saw Elba.a"
    val r4 = isPalindrome(s4)
    println(r4)
}