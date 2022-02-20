package dev.dghg.tdd.coffee

class CoffeeMachine(
    private val max_amount: Int = 1000,
    private val price: Int = 100
) {
    private var money: Money? = null
    private var beans: Beans? = null
    val hasBean: Boolean
        get() {
            return this.beanAmount > 0
        }
    val beanAmount: Int
        get() {
            return this.beans?.amount ?: 0
        }
    val isAvailable: Boolean
        get() {
            return this.beanAmount > 0 && this.money!!.amount >= this.price
        }
    private fun checkInsertBeans(beans: Beans): Boolean {
        if(beans.isUsed) {
            throw AlreadyUsedBeanException()
        }
        return if(this.hasBean) {
            if (this.beanAmount + beans.amount > this.max_amount) {
                throw OverBeanException()
            } else if (!this.beans!!.type.equals(beans.type)) {
                throw AlreadyExistsBeanException()
            } else {
                true
            }
        } else {
            beans.amount <= this.max_amount
        }
    }
    fun insertBean(beans: Beans) {
        if(this.checkInsertBeans(beans)) {
            beans.use()
            this.beans = Beans(beans.type, beans.amount + (this.beans?.amount ?: 0))
            this.beans!!.use()
        }
    }

    fun insertMoney(money: Money) {
        this.money = if(this.money == null) {
            money
        } else {
            Money(money.amount + this.money!!.amount)
        }
    }

    fun makeCoffee(): Coffee {
        if(this.isAvailable) {
            return Coffee(BeanType.LUWAK, 1)
        } else {
            throw NotAvailableException()
        }
    }

}
class OverBeanException: RuntimeException()
class AlreadyExistsBeanException: RuntimeException()
class AlreadyUsedBeanException: RuntimeException()
class NotAvailableException: RuntimeException()