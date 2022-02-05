package dev.dghg.tdd.currency

interface Expression {
    fun reduce(bank: Bank, to: String): Money
}
