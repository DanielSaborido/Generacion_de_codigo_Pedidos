package classes

import java.util.*

abstract class CPayment {
    abstract fun calcularCantidad(): Float
}
data class Card(val number: String, val expirationDate: Date, val typeCard: String, val amount: Float): CPayment() {
    override fun calcularCantidad(): Float {
        return amount
    }
}
data class Cash(val typeCard: String, val amount: Float): CPayment() {
    override fun calcularCantidad(): Float {
        return amount
    }
}
data class Cheque(val name: String, val bank: String, val amount: Float): CPayment() {
    override fun calcularCantidad(): Float {
        return amount
    }
}