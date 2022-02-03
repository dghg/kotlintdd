package dev.dghg.tdd.currency

open class Money(
    protected open val amount: Int,
    protected open val currency: String
) {
    companion object {
        fun dollar(amount: Int): Dollar {
            return Dollar(amount, "USD")
        }
        fun franc(amount: Int): Franc {
            return Franc(amount, "CHF")
        }
    }
    override fun equals(other: Any?): Boolean {
        val money: Money = other as Money
        return money.amount == amount && money.currency == currency
    }

    override fun toString(): String {
        return "$amount $currency"
    }
    fun times(multiplier: Int): Money {
        return Money(amount * multiplier, currency)
    }
}