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
    override fun times(multiplier: Int): Expression {
        return Money(amount * multiplier, currency)
    }

    override fun plus(addend: Expression): Expression {
        return Sum(this, addend)
    }

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to)
        return Money(amount / rate, to)
    }
}