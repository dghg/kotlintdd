package dev.dghg.tdd.currency

class Dollar(amount: Int, currency: String): Money(amount, currency) {
    override fun hashCode(): Int {
        return 1
    }
}