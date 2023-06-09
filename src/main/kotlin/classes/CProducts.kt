package classes

import entities.Products
import interfaces.DataSource
import interfaces.IDataAccess
import java.util.*
class CProducts(private val dataSource: DataSource) : IDataAccess<Products> {
    override fun create(entity: Products): Products {
        val sql = "INSERT INTO PRODUCTS (id, name, description, price, taxes, stock) VALUES (?, ?, ?, ?, ?, ?)"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, entity.id.toString())
                stmt.setString(2, entity.name)
                stmt.setString(3, entity.description)
                stmt.setString(4, entity.price.toString())
                stmt.setString(5, entity.taxes.toString())
                stmt.setString(6, entity.stock.toString())
                when(stmt.executeUpdate()) {
                    else -> entity
                }
            }
        }
    }

    override fun getByName(name: String): Products? {
        val sql = "SELECT * FROM PRODUCTS WHERE id = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, name)
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    Products(
                        id = UUID.fromString(rs.getString("id")),
                        name = rs.getString("name"),
                        description = rs.getString("description"),
                        price = rs.getFloat("price"),
                        taxes = rs.getInt("taxes"),
                        stock = rs.getInt("stock")
                    )
                } else {
                    null
                }
            }
        }
    }

    override fun getAll(): MutableList<Products> {
        val sql = "SELECT * FROM PRODUCTS"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                val products = mutableListOf<Products>()
                while (rs.next()) {
                    products.add(
                        Products(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                            description = rs.getString("description"),
                            price = rs.getFloat("price"),
                            taxes = rs.getInt("taxes"),
                            stock = rs.getInt("stock")
                        )
                    )
                }
                products
            }
        }
    }

    override fun update(entity: Products): Products {
        val sql = "UPDATE PRODUCTS SET name = ?, description = ?, price = ?, taxes = ?, stock = ? WHERE id = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, entity.stock.toString())
                stmt.setString(2, entity.taxes.toString())
                stmt.setString(3, entity.price.toString())
                stmt.setString(4, entity.description)
                stmt.setString(5, entity.name)
                stmt.setString(6, entity.id.toString())
                stmt.executeUpdate()
                entity
            }
        }
    }

    override fun delete(id: Any) {
        val sql = "DELETE FROM PRODUCTS WHERE id = ?"
        dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                stmt.executeUpdate()
            }
        }
    }
    fun updateStock(ID: String, STOCK: Int): Int {
        val sql = "UPDATE PRODUCTS SET stock = (stock - ?) WHERE id = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, STOCK)
                stmt.setString(2, ID)
                stmt.executeUpdate()
            }
        }
    }
}
