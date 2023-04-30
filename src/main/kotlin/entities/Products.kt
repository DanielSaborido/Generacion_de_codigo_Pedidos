package entities

import java.util.*
data class Products(var id: UUID = UUID.randomUUID(), var name: String, var description: String, var price: Float, var taxes: Int, var stock: Int)