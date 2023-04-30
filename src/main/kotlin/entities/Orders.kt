package entities
import java.util.*

data class Orders(var id: UUID = UUID.randomUUID(), var owner: String, var products: List<Products>, var debt: Int, var date: Date, var state: stateOrder = stateOrder.outstanding)

enum class stateOrder {
    outstanding,paid,indicted,sent,delivered
}