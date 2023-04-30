package interfaces

import entities.Orders
import java.util.*

interface IOrder {
    fun create(user: Orders):Orders?
    fun getAll(): List<Orders>
    fun getById(id: UUID): Orders?
    fun update(user: Orders):Orders
    fun delete(id: UUID)
}