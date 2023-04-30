import entities.Client
import interfaces.DataSource
import interfaces.IDataAccess

class CClient(private val dataSource: DataSource) : IDataAccess<Client> {
    override fun create(entity: Client): Client? {
        val sql = "INSERT INTO CLIENT (dni, name, phone, mail) VALUES (?, ?, ?, ?)"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, entity.dni)
                stmt.setString(2, entity.name)
                stmt.setString(3, entity.phone.toString())
                stmt.setString(4, entity.mail)
                when(stmt.executeQuery()) {
                    null -> null
                    else -> entity
                }
            }
        }
    }

    override fun getById(id: Any): Client? {
        val sql = "SELECT * FROM CLIENT WHERE dni = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
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

    override fun update(entity: Client): Client {
        val sql = "UPDATE CLIENT SET name = ?, phone = ?, mail = ? WHERE dni = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, entity.mail)
                stmt.setString(2, entity.phone.toString())
                stmt.setString(3, entity.name)
                stmt.setString(4, entity.dni)
                stmt.executeUpdate()
                entity
            }
        }
    }

    override fun delete(id: Any) {
        val sql = "DELETE FROM CLIENT WHERE dni = ?"
        dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                stmt.executeUpdate()
            }
        }
    }
}
