package dev.dghg.tdd.currency

class Bank {
    private var rates: MutableMap<RateKey, Int> = mutableMapOf()
    fun reduce(source: Expression, to: String): Money {
        return source.reduce(this, to)
    }

    fun addRate(src: String, dest: String, rate: Int) {
        val rateKey = RateKey(src, dest)
        rates[rateKey] = rate
    }

    fun rate(src: String, dest: String): Int {
        if (src == dest) {
            return 1
        }
        val rateKey = RateKey(src, dest)
        return rates[rateKey] ?: throw IllegalArgumentException("No rate defined")
    }
}

data class RateKey(
    val from: String,
    val to: String
)