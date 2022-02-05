package dev.dghg.tdd.currency

interface Expression {
    fun reduce(to: String): Money
}
