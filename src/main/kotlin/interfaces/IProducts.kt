package interfaces

import entities.Products
import java.util.*

interface Product {
    fun create(user: Products):Products?
    fun getAll(): List<Products>
    fun getById(id: UUID): Products?
    fun update(user: Products):Products
    fun delete(id: UUID)
    fun delete(user: Products):Products?
}