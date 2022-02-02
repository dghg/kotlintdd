package dev.dghg.tdd

import dev.dghg.tdd.currency.Dollar
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe


internal class CurrencyTest: AnnotationSpec() {

    @BeforeEach
    fun setup() {
        println("Setup : BeforeEach")
    }
    @Test
    fun `multiplication test`() {
        val fiveDollar = Dollar(5)
        var product = fiveDollar.times(2)
        product.amount shouldBe 10
        product = fiveDollar.times(3)
        product.amount shouldBe 15
    }
}