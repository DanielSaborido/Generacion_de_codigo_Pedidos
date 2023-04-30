import entities.Client
import interfaces.DataSource
import interfaces.IClient

class CClient(private val dataSource: DataSource) : IClient {
    override fun create(user: Client): Client? {
        val sql = "INSERT INTO CLIENT (dni, name, phone, mail) VALUES (?, ?, ?, ?)"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.dni)
                stmt.setString(2, user.name)
                stmt.setString(3, user.phone.toString())
                stmt.setString(4, user.mail)
                when(stmt.executeQuery()) {
                    null -> null
                    else -> user
                }
            }
        }
    }

    override fun getById(dni: String): Client? {
        val sql = "SELECT * FROM CLIENT WHERE dni = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, dni)
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    Client(
                        dni = rs.getString("dni"),
                        name = rs.getString("name"),
                        phone = rs.getInt("phone"),
                        mail = rs.getString("mail")
                    )
                } else {
                    null
                }
            }
        }
    }

    override fun getAll(): MutableList<Client> {
        val sql = "SELECT * FROM CLIENT"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                val users = mutableListOf<Client>()
                while (rs.next()) {
                    users.add(
                        Client(
                            dni = rs.getString("dni"),
                            name = rs.getString("name"),
                            phone = rs.getInt("phone"),
                            mail = rs.getString("mail")
                        )
                    )
                }
                users
            }
        }
    }

    override fun update(user: Client): Client {
        val sql = "UPDATE CLIENT SET name = ?, phone = ?, mail = ? WHERE dni = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.mail)
                stmt.setString(2, user.phone.toString())
                stmt.setString(3, user.name)
                stmt.setString(4, user.dni)
                stmt.executeUpdate()
                user
            }
        }
    }

    override fun delete(dni: String) {
        val sql = "DELETE FROM CLIENT WHERE dni = ?"
        dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, dni)
                stmt.executeUpdate()
            }
        }
    }
}
