import kotlin.math.max

fun main() {

    println(maxMultiply(59))
    println(maxSimpleMultiply(59))

}

/*
给你一根长度为n的绳子，请把绳子剪成整数长度m段，（m，n都是整数，n>1并且m>1),每段绳子的长度记为k[0],k[1],k[2]...k[m-1],
请问k[0]*k[1]*...*k[m-1]可能的最大乘积是多少？
 */
fun maxMultiply(n: Int): Int {
    val list = arrayListOf<Int>()
    for (i in 0 .. n + 1) {
        list.add(0)
    }
    list[1] = 1
    list[2] = 2
    for (i in 3 .. n) {
        for (j in 2 .. i) {
            list[i] = max(list[i], max(j * list[i-j], j * (i-j)))
        }
    }
    return list[n]
}

fun maxSimpleMultiply(n: Long): Long {
    var sum = 1L
    val count = n / 3
    val num = n % 3
    when ( num ) {
        0L -> {
            for ( i in 1 .. count) {
                sum *= 3
            }
        }
        1L -> {
            for ( i in 1 until count) {
                sum *= 3
            }
            sum *= 4
        }
        2L -> {
            for ( i in 1 .. count) {
                sum *= 3
            }
            sum *= 2
        }
    }
    return sum
}