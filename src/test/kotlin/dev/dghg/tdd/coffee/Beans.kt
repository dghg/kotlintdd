package dev.dghg.tdd.coffee

class Beans(val type: BeanType, val amount: Int) {
    var isUsed = false
    fun use() {
        isUsed = true
    }
}
