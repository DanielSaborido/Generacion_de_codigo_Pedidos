package entities

import java.util.*

data class Order(var id: UUID = UUID.randomUUID(), var name: String, var description: String, var price: Int, var taxes: Int, var stock: Int)