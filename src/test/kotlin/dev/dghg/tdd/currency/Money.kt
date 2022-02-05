package dev.dghg.tdd.currency

open class Money(
    open val amount: Int,
    protected open val currency: String
): Expression {
    companion object {
        fun dollar(amount: Int): Money {
            return Money(amount, "USD")
        }
        fun franc(amount: Int): Money {
            return Money(amount, "CHF")
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

    fun plus(money: Money): Expression {
        return Sum(this, money)
    }

    override fun reduce(to: String): Money {
        return this
    }
}