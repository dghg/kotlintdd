package dev.dghg.tdd

import dev.dghg.tdd.currency.Bank
import dev.dghg.tdd.currency.Expression
import dev.dghg.tdd.currency.Money
import dev.dghg.tdd.currency.Sum
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe


internal class CurrencyTest: AnnotationSpec() {

    @BeforeEach
    fun setup() {
        println("Setup : BeforeEach")
    }
    @Test
    fun `multiplication test`() {
        val fiveDollar = Money.dollar(5)
        val fiveFranc = Money.franc(5)
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
        val bank: Bank = Bank()
        val reduced = bank.reduce(sum, "USD")
        reduced shouldBe Money.dollar(10)
    }

    @Test
    fun `plus return sum test`() {
        val fiveDollar = Money.dollar(5)
        val result: Expression = fiveDollar.plus(fiveDollar)
        val sum = result as Sum
        sum.augend shouldBe fiveDollar
        sum.addend shouldBe fiveDollar
    }

    @Test
    fun `reduce argument Money test`() {
        val bank = Bank()
        val reduced = bank.reduce(Money.dollar(5), "USD")
        reduced shouldBe Money.dollar(5)
    }

    @Test
    fun `money rate test`() {
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val twoFranc = Money.franc(2)
        twoFranc.reduce(bank, "CHF") shouldBe Money.franc(2)
        twoFranc.reduce(bank, "USD") shouldBe Money.dollar(1)
    }
}