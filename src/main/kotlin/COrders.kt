import entities.Orders
import interfaces.DataSource
import interfaces.IOrder
import java.util.*

class COrders(private val dataSource: DataSource) : IOrder {
    override fun create(user: Orders): Orders? {
        val sql = "INSERT INTO ORDERS (id, owner, orderSize, orderPrice, date, state) VALUES (?, ?, ?, ?, ?, ?)"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.id.toString())
                stmt.setString(2, user.owner)
                stmt.setString(3, user.orderSize.toString())
                stmt.setString(4, user.orderPrice.toString())
                stmt.setString(5, user.date.toString())
                stmt.setString(6, user.state.toString())
                when(stmt.executeQuery()) {
                    null -> null
                    else -> user
                }
            }
        }
    }

    override fun getById(id: UUID): Orders? {
        val sql = "SELECT * FROM ORDERS WHERE id = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    Orders(
                        id = UUID.fromString(rs.getString("id")),
                        owner = rs.getString("owner"),
                        orderSize = rs.getInt("orderSize"),
                        orderPrice = rs.getInt("orderPrice"),
                        date = rs.getDate("date"),
                        state = rs.getString("state")
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
                val users = mutableListOf<Orders>()
                while (rs.next()) {
                    users.add(
                        Orders(
                            id = UUID.fromString(rs.getString("id")),
                            owner = rs.getString("owner"),
                            orderSize = rs.getInt("orderSize"),
                            orderPrice = rs.getInt("orderPrice"),
                            date = rs.getDate("date"),
                            state = rs.getString("state")
                        )
                    )
                }
                users
            }
        }
    }

    override fun update(user: Orders): Orders {
        val sql = "UPDATE ORDERS SET owner = ?, orderSize = ?, orderPrice = ?, date = ?, state = ? WHERE id = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.state.toString())
                stmt.setString(2, user.date.toString())
                stmt.setString(3, user.orderPrice.toString())
                stmt.setString(4, user.orderSize.toString())
                stmt.setString(5, user.owner)
                stmt.setString(6, user.id.toString())
                stmt.executeUpdate()
                user
            }
        }
    }

    override fun delete(id: UUID) {
        val sql = "DELETE FROM ORDERS WHERE id = ?"
        dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                stmt.executeUpdate()
            }
        }
    }
}
