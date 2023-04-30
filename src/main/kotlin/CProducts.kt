import entities.Products
import interfaces.DataSource
import interfaces.IProduct
import java.util.*

class CProducts(private val dataSource: DataSource) : IProduct {
    override fun create(user: Products): Products? {
        val sql = "INSERT INTO PRODUCTS (id, name, description, price, taxes, stock) VALUES (?, ?, ?, ?, ?, ?)"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.id.toString())
                stmt.setString(2, user.name)
                stmt.setString(3, user.description)
                stmt.setString(4, user.price.toString())
                stmt.setString(5, user.taxes.toString())
                stmt.setString(6, user.stock.toString())
                when(stmt.executeQuery()) {
                    null -> null
                    else -> user
                }
            }
        }
    }

    override fun getById(id: UUID): Products? {
        val sql = "SELECT * FROM PRODUCTS WHERE id = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    Products(
                        id = UUID.fromString(rs.getString("id")),
                        name = rs.getString("name"),
                        description = rs.getString("description"),
                        price = rs.getString("price").toInt(),
                        taxes = rs.getString("taxes").toInt(),
                        stock = rs.getString("stock").toInt()
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
                val users = mutableListOf<Products>()
                while (rs.next()) {
                    users.add(
                        Products(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                            description = rs.getString("description"),
                            price = rs.getString("price").toInt(),
                            taxes = rs.getString("taxes").toInt(),
                            stock = rs.getString("stock").toInt()
                        )
                    )
                }
                users
            }
        }
    }

    override fun update(user: Products): Products {
        val sql = "UPDATE PRODUCTS SET name = ?, description = ?, price = ?, taxes = ?, stock = ? WHERE id = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.stock.toString())
                stmt.setString(2, user.taxes.toString())
                stmt.setString(3, user.price.toString())
                stmt.setString(4, user.description)
                stmt.setString(5, user.name)
                stmt.setString(6, user.id.toString())
                stmt.executeUpdate()
                user
            }
        }
    }

    override fun delete(id: UUID) {
        val sql = "DELETE FROM PRODUCTS WHERE id = ?"
        dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                stmt.executeUpdate()
            }
        }
    }
}
