package dev.dghg.tdd

import dev.dghg.tdd.coffee.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CoffeeMachineTest: AnnotationSpec() {
    var coffeeMachine = CoffeeMachine()
    var luwakBeans = Beans(BeanType.LUWAK, 100)
    var sbuxBeans = Beans(BeanType.SBUX, 100)
    @BeforeEach
    fun setup() {
        coffeeMachine = CoffeeMachine()
        luwakBeans = Beans(BeanType.LUWAK, 100)
        sbuxBeans = Beans(BeanType.SBUX, 100)
    }

    @Test
    fun `원두를 생성한다`() {
        val beans = Beans(BeanType.LUWAK, 100)
        beans.type shouldBe BeanType.LUWAK
        beans.amount shouldBe 100
    }

    @Test
    fun `원두를 투입한다`() {
        coffeeMachine.insertBean(luwakBeans)
        coffeeMachine.hasBean shouldBe true
    }

    @Test
    fun `원두를 투입, 다른 원두 존재하면 에러 반환`() {
        coffeeMachine.insertBean(luwakBeans)
        shouldThrow<AlreadyExistsBeanException> {
            coffeeMachine.insertBean(sbuxBeans)
        }
    }

    @Test
    fun `원두를 투입, 같은 원두 투입`() {
        coffeeMachine.insertBean(luwakBeans)
        val luwakBeansMore = Beans(BeanType.LUWAK, 200)
        coffeeMachine.insertBean(luwakBeansMore)

        coffeeMachine.hasBean shouldBe true
        coffeeMachine.beanAmount shouldBe 300
    }

    @Test
    fun `원두 max amount 테스트`() {
        coffeeMachine.insertBean(luwakBeans)
        val luwakBeansMore = Beans(BeanType.LUWAK, 12345)
        shouldThrow<OverBeanException> {
            coffeeMachine.insertBean(luwakBeansMore)
        }
    }

    @Test
    fun `같은 원두는 두번 들어갈 수 없음`() {
        coffeeMachine.insertBean(luwakBeans)
        shouldThrow<AlreadyUsedBeanException> {
            coffeeMachine.insertBean(luwakBeans)
        }
    }

    @Test
    fun `돈을 투입한다`() {
        coffeeMachine.insertMoney(Money(100)) // how ?
    }

    @Test
    fun `원두가 없으면 추출 불가`() {
        shouldThrow<NotAvailableException> {
           coffeeMachine.makeCoffee()
        }
    }

    @Test
    fun `원두가 있고 돈이 없으면 추출 불가`() {
        coffeeMachine.insertBean(luwakBeans)
        shouldThrow<NotAvailableException> {
            coffeeMachine.makeCoffee()
        }
    }

    @Test
    fun `원두와 돈을 투입하고 추출`() {
        coffeeMachine.insertBean(luwakBeans)
        coffeeMachine.insertMoney(Money(100))
        val coffee = coffeeMachine.makeCoffee()
        coffee shouldNotBe null
    }

}