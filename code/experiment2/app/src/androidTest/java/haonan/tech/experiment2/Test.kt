package haonan.tech.experiment2

import java.lang.StringBuilder

fun getRandomLengthString(str: String):String{
    val n = (1..20).random()
    val builder = StringBuilder()
    repeat(n){
        builder.append(str)
    }
    return builder.toString()
}

operator fun String.times(n: Int) = repeat(n)

fun main(){
    println(getRandomLengthString("str"))
    // 更优雅的写法
    println("str" * (1..20).random())
}