package entities
import java.util.*
import java.sql.Date

data class Orders(var id: UUID = UUID.randomUUID(), var owner: String, var products: String, var debt: String, var amount: Int, var date: Date = Date(System.currentTimeMillis()), var state: stateOrder = stateOrder.outstanding)

enum class stateOrder {
    outstanding,paid,indicted,sent,delivered
}