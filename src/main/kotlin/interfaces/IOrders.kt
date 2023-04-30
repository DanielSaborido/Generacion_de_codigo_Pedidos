package interfaces

import entities.Orders
import java.util.*

interface Order {
    fun create(user: Orders):Orders?
    fun getAll(): List<Orders>
    fun getById(id: UUID): Orders?
    fun update(user: Orders):Orders
    fun delete(id: UUID)
    fun delete(user: Orders):Orders?
}