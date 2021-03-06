package dev.dghg.tdd.currency

class Sum(
    val augend: Money,
    val addend: Money
): Expression {
    override fun reduce(bank: Bank, to: String): Money {
        val amount = augend.amount + addend.amount
        return Money(amount, to)
    }
}
