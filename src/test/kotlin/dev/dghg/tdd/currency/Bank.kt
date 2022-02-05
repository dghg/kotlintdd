package dev.dghg.tdd.currency

class Bank {
    fun reduce(source: Expression, to: String): Money {
        return source.reduce(to)
    }
}
