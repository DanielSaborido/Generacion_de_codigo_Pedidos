package entities
import java.util.*
import java.sql.Date

data class Orders(var id: UUID = UUID.randomUUID(), var owner: String, var products: String, var debt: String, var amount: Int, var date: Date = Date(System.currentTimeMillis()), var state: stateOrder = stateOrder.Outstanding)

enum class stateOrder {
    Outstanding,Paid,Indicted,Sent,Delivered
}