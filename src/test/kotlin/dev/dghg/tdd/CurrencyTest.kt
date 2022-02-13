package dev.dghg.tdd

import dev.dghg.tdd.currency.Bank
import dev.dghg.tdd.currency.Expression
import dev.dghg.tdd.currency.Money
import dev.dghg.tdd.currency.Sum
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe


internal class CurrencyTest: AnnotationSpec() {

    private var bank = Bank()
    private var fiveDollar = Money.dollar(5)
    private var fiveFranc = Money.franc(5)
    private var tenFranc = Money.franc(10)
    @BeforeEach
    fun setup() {
        bank = Bank()
        bank.addRate("CHF", "USD", 2)
        var fiveDollar = Money.dollar(5)
        var fiveFranc = Money.franc(5)
        var tenFranc = Money.franc(10)
        println("Before Each")
    }
    @Test
    fun `multiplication test`() {
        fiveDollar.apply {
            Money.dollar(10) shouldBe times(2)
            Money.dollar(15) shouldBe times(3)
        }
        fiveFranc.apply {
            Money.franc(10) shouldBe times(2)
            Money.franc(15) shouldBe times(3)

        }
    }

    @Test
    fun `Equality test`() {
        Money.dollar(6) shouldNotBe Money.dollar(5)
        Money.dollar(5) shouldBe Money.dollar(5)
        Money.dollar(5) shouldNotBe Money.franc(5)
    }

    @Test
    fun `addition test`() {
        val sum: Expression = Money.dollar(5).plus(Money.dollar(5))
        val reduced = bank.reduce(sum, "USD")
        reduced shouldBe Money.dollar(10)
    }

    @Test
    fun `plus return sum test`() {
        val result: Expression = fiveDollar.plus(fiveDollar)
        val sum = result as Sum
        sum.augend shouldBe fiveDollar
        sum.addend shouldBe fiveDollar
    }

    @Test
    fun `reduce argument Money test`() {
        val reduced = bank.reduce(Money.dollar(5), "USD")
        reduced shouldBe Money.dollar(5)
    }

    @Test
    fun `money rate test`() {
        val twoFranc = Money.franc(2)
        twoFranc.reduce(bank, "CHF") shouldBe Money.franc(2)
        twoFranc.reduce(bank, "USD") shouldBe Money.dollar(1)
    }

    @Test
    fun `mixed addition test`() {
        val sum: Expression = fiveDollar.plus(tenFranc)
        val result = bank.reduce(sum, "USD")

        result shouldBe Money.dollar(10)
    }

    @Test
    fun `sum plus test`() {
        val sum: Expression = fiveDollar.plus(tenFranc).plus(fiveDollar)
        val result = bank.reduce(sum, "USD")
        result shouldBe Money.dollar(15)
    }

    @Test
    fun `sum times test`() {
        val sum: Expression = fiveDollar.plus(tenFranc).times(2)
        val result = bank.reduce(sum, "USD")
        result shouldBe Money.dollar(20)
    }
}