import sun.misc.Queue
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val array = arrayListOf(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1)
    val root = buildTree(array)
    val path = pathSum(root, 22)



    path.forEach {
        print("[")
        it.forEach { item ->
            print("$item,")
        }
        println("]")
    }
}

class TreeNode(
    var `val`: Int,
    var left: TreeNode?,
    var right: TreeNode?
)

fun buildTree(tree: ArrayList<Int?>): TreeNode? {
    if (tree.isEmpty() || tree[0] == null) {
        return null
    } else {
        val treeArray: ArrayList<TreeNode> = arrayListOf()
        var currentParentIndex: Int? = null
        var inputLeft = false
        var inputRight = false
        tree.forEach {
            if (it != null) {
                val node = TreeNode(it, null, null)
                treeArray.add(node)
                if (currentParentIndex == null) {
                    currentParentIndex = 0
                } else {
                    if (inputLeft && inputRight) {
                        if (currentParentIndex != null) {
                            inputLeft = false
                            inputRight = false
                            currentParentIndex = currentParentIndex!! + 1
                        }
                    }
                    if (!inputLeft) {
                        inputLeft = true
                        treeArray[currentParentIndex!!].left = node
                    } else {
                        inputRight = true
                        treeArray[currentParentIndex!!].right = node
                    }
                }
            } else {
                if (inputLeft && inputRight) {
                    if (currentParentIndex != null) {
                        inputLeft = false
                        inputRight = false
                        currentParentIndex = currentParentIndex!! + 1
                    }
                }
                if (!inputLeft) {
                    inputLeft = true
                } else {
                    inputRight = true
                }
            }
        }
        return treeArray.firstOrNull()
    }
}

fun searchPath(tree: ArrayList<Int?>, targetSum: Int): ArrayList<List<Int>> {
    if (tree.isEmpty()) {
        return arrayListOf()
    }
    var correctPath: ArrayList<List<Int>> = arrayListOf()
    var sum = 0
    var currentNodeIndex = 0
    var path: ArrayList<Int> = arrayListOf()
    while (true) {
        while (currentNodeIndex < tree.size && tree[currentNodeIndex] != null) {
            sum += tree[currentNodeIndex]!!
            path.add(currentNodeIndex)
            if (sum > targetSum) {
                break
            }
            currentNodeIndex = currentNodeIndex * 2 + 1
            // 如果左节点为空
            if (currentNodeIndex > tree.size || tree[currentNodeIndex] == null) {
                // 右节点
                currentNodeIndex += 1
            }
        }
        if (sum == targetSum) {
            correctPath.add(path.mapNotNull { tree[it] })
        }
        if (path.size > 1) {
            currentNodeIndex = path.removeLast()
            sum -= tree[currentNodeIndex] ?: 0
            while (path.size > 0 && path.last() * 2 + 2 == currentNodeIndex) {
                currentNodeIndex = path.removeLast()
                sum -= tree[currentNodeIndex] ?: 0
            }
            if (path.isEmpty()) {
                break
            } else {
                currentNodeIndex = path.last() * 2 + 2
            }
        } else {
            break
        }
    }

    return correctPath
}

fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
//    var list = arrayListOf<List<Int>>()
//    var currentNode = root
//    var stack = Stack<TreeNode>()
//    var sum = 0
//    while (true) {
//        while (currentNode != null) {
//            sum += currentNode.`val`
//            stack.push(currentNode)
//            if (sum > targetSum) {
//                break
//            }
//            val parent = currentNode
//            currentNode = parent.left
//            // 如果左节点为空
//            if (currentNode == null) {
//                // 右节点
//                currentNode = parent.right
//            }
//        }
//        if (sum == targetSum) {
//            list.add(stack.mapNotNull { it.`val` })
//        }
//        if (stack.size > 1) {
//            currentNode = stack.pop()
//            sum -= currentNode.`val`
//            while (stack.isNotEmpty() && stack.last().right == currentNode) {
//                currentNode = stack.pop()
//                sum -= currentNode.`val`
//            }
//            if (stack.isEmpty()) {
//                break
//            } else {
//                currentNode = stack.last().right
//            }
//        } else {
//            break
//        }
//    }
//    return list
    var list = arrayListOf<List<Int>>()
    var currentNode = root
    var stack = Stack<TreeNode>()
    var sum = 0
    var parentQueue = ArrayList<TreeNode>()
    while(stack.isNotEmpty() || currentNode == root) {
        while(currentNode != null) {
            sum += currentNode.`val`
            stack.push(currentNode)
            if (currentNode.left == null && currentNode.right == null && sum == targetSum){
                list.add(stack.map {it.`val`})
            }
            currentNode = currentNode.left
        }
        if (parentQueue.isNotEmpty()) {
            var parentNode = parentQueue.first()
            while (parentNode.right == currentNode) {
                parentQueue.removeFirst()
                sum -= parentNode.`val`
                currentNode = parentNode
                if (parentQueue.isNotEmpty()) {
                    parentNode = parentQueue.first()
                }
            }
        }
        if (stack.isNotEmpty()) {
            currentNode = stack.pop()
            parentQueue.add(currentNode)
            currentNode = currentNode.right
        }
    }
    return list
}

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        var list = arrayListOf<List<Int>>()
        var currentNode = root
        var stack = Stack<TreeNode>()
        var sum = 0
        while (true) {
            while (currentNode != null) {
                sum += currentNode.`val`
                stack.push(currentNode)
                if (sum > targetSum) {
                    break
                }
                val parent = currentNode
                currentNode = parent.left
                // 如果左节点为空
                if (currentNode == null) {
                    // 右节点
                    currentNode = parent.right
                }
            }
            if (sum == targetSum) {
                list.add(stack.mapNotNull { it.`val` })
            }
            if (stack.size > 1) {
                currentNode = stack.pop()
                sum -= currentNode.`val`
                while (stack.isNotEmpty() && stack.last().right == currentNode) {
                    currentNode = stack.pop()
                    sum -= currentNode.`val`
                }
                if (stack.isEmpty()) {
                    break
                } else {
                    currentNode = stack.last().right
                }
            } else {
                break
            }
        }
        return list
    }
}