package dev.dghg.tdd

import dev.dghg.tdd.currency.Dollar
import dev.dghg.tdd.currency.Franc
import dev.dghg.tdd.currency.Money
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
        Money.franc(6) shouldNotBe Money.franc(5)
        Money.franc(5) shouldBe Money.franc(5)
        Money.dollar(5) shouldNotBe Money.franc(5)
    }

    @Test
    fun `test Different Class equality`() {
        Money(10, "CHF") shouldBe Franc(10, "CHF")
    }
}