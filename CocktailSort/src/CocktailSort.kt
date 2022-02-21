fun main() {
    val array = arrayListOf(10,9,8,7,6,5,4,3,2,1,0)
    sort(array)
}

// 鸡尾酒排序
fun sort(arr: ArrayList<Int>) {
    var temp = 0
    var rightLastChangeIndex = arr.size - 1
    var leftLastChangeIndex = 0
    var isSorted = true
    for (i in 0 until arr.size/2) {
        isSorted = true
        println("j in $i .. ${rightLastChangeIndex - 1}")
        for (j in i until rightLastChangeIndex) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
                rightLastChangeIndex = j
                isSorted = false
            }
        }
        println(arr)
        if (isSorted) {
            break
        }
        isSorted = true
        println("j in ${arr.size - 2 - i} downTo ${leftLastChangeIndex + 1}")
        for (j in arr.size - 1 - i downTo leftLastChangeIndex + 1) {
            if (arr[j] < arr[j - 1]) {
                temp = arr[j]
                arr[j] = arr[j-1]
                arr[j-1] = temp
                leftLastChangeIndex = j
                isSorted = false
            }
        }
        if (isSorted) {
            break
        }
        println(arr)
    }
}