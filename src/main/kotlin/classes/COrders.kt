package classes

import entities.Orders
import entities.stateOrder
import interfaces.DataSource
import interfaces.IDataAccess
import java.util.*

class COrders(private val dataSource: DataSource) : IDataAccess<Orders> {
    override fun create(entity: Orders): Orders {
        val sql = "INSERT INTO ORDERS (id, owner, products, debt, amount, date) VALUES ( ?, ?, ?, ?, ? ,?)"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, entity.id.toString())
                stmt.setString(2, entity.owner)
                stmt.setString(3, entity.products)
                stmt.setString(4, entity.debt.toString())
                stmt.setString(5, entity.amount.toString())
                stmt.setString(6, entity.date.toString())
                when(stmt.executeUpdate()) {
                    else -> entity
                }
            }
        }
    }

    override fun getByName(name: String): Orders? {
        val sql = "SELECT * FROM ORDERS WHERE owner = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(2, name)
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    Orders(
                        id = UUID.fromString(rs.getString("id")),
                        owner = rs.getString("owner"),
                        products = rs.getString("products"),
                        debt = rs.getFloat("orderPrice"),
                        amount = rs.getInt("orderPrice"),
                        date = rs.getDate("date"),
                        state = stateOrder.valueOf(rs.getString("state"))
                    )
                } else {
                    null
                }
            }
        }
    }

    override fun getAll(): MutableList<Orders> {
        val sql = "SELECT * FROM ORDERS"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                val orders = mutableListOf<Orders>()
                while (rs.next()) {
                    orders.add(
                        Orders(
                            id = UUID.fromString(rs.getString("id")),
                            owner = rs.getString("owner"),
                            products = rs.getString("products"),
                            debt = rs.getFloat("orderPrice"),
                            amount = rs.getInt("orderPrice"),
                            date = rs.getDate("date"),
                            state = stateOrder.valueOf(rs.getString("state"))
                        )
                    )
                }
                orders
            }
        }
    }

    override fun update(entity: Orders): Orders {
        val sql = "UPDATE ORDERS SET state = ? WHERE owner = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, entity.state.toString())
                stmt.setString(2, entity.date.toString())
                stmt.setString(3, entity.debt.toString())
                stmt.setString(4, entity.products)
                stmt.setString(5, entity.owner)
                stmt.setString(6, entity.id.toString())
                stmt.executeUpdate()
                entity
            }
        }
    }

    override fun delete(id: Any) {
        val sql = "DELETE FROM ORDERS WHERE id = ?"
        dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                stmt.executeUpdate()
            }
        }
    }
    fun getDebt(name: String): Float {
        val sql = "SELECT SUM(debt) FROM ORDERS WHERE owner = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, name)
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    rs.getFloat("SUM(debt)")
                } else {
                    0f
                }
            }
        }
    }
}
