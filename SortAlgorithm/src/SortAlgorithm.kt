fun main() {

    var string = "[{\"id\": 13, \"name\": \"df\", \"industry\": \"325、asd\", \"source\": \"zzzz\", \"customer_type\": \"danss\", \"scale\": \"0-15人\", \"link_name\": \"df\", \"link_phone\": \"18211111111\", \"business_category\": \"xxxxx\", \"tag_ids\": \"Spam、Created by Partner\", \"deal_state\": \"未成交\", \"consumption_amount\": 0.0, \"high_seas\": false}]"


    val array = arrayListOf(4,4,6,5,3,2,8,1)
    quickSort(array,0,array.size - 1)
    println("$array")
}

/**
 * 快排
 */
fun quickSort(arr: ArrayList<Int>, startIndex: Int, endIndex: Int) {
    println("startIndex: $startIndex, endIndex: $endIndex")
    if (startIndex == endIndex) {
        return
    }
    var temp = 0
    var leftIndex = startIndex + 1
    var rightIndex = endIndex
    while (leftIndex < rightIndex){
        println("leftIndex: $leftIndex, rightIndex: $rightIndex")
        println("$arr")
        if (arr[rightIndex] < arr[startIndex]) {
            if (arr[leftIndex] > arr[startIndex]) {
                temp = arr[leftIndex]
                arr[leftIndex] = arr[rightIndex]
                arr[rightIndex] = temp
                println("$arr")
                rightIndex --
            } else {
                leftIndex ++
            }
        } else {
            rightIndex --
        }
    }
    println(leftIndex)
    var standardIndex = leftIndex
    when {
        arr[startIndex] > arr[standardIndex] -> {
            temp = arr[startIndex]
            arr[startIndex] = arr[standardIndex]
            arr[standardIndex] = temp
            println("$arr")
        }
        startIndex < leftIndex - 1 -> {
            standardIndex = leftIndex - 1
            temp = arr[startIndex]
            arr[startIndex] = arr[standardIndex]
            arr[standardIndex] = temp
            println("$arr")
        }
        startIndex == leftIndex - 1 -> {
            standardIndex = leftIndex - 1
        }
    }
    if (startIndex < standardIndex - 1) {
        quickSort(arr, startIndex, standardIndex - 1)
    }
    if (standardIndex + 1 < endIndex) {
        quickSort(arr, standardIndex + 1, endIndex)
    }
}