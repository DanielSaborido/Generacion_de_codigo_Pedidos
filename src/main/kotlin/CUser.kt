import entities.User
import interfaces.DataSource
import interfaces.IDataAccess

class CUser(private val dataSource: DataSource) : IDataAccess<User> {
    override fun create(entity: User): User? {
        val sql = "INSERT INTO USERS (dni, name, phone, mail) VALUES (?, ?, ?, ?)"
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

    override fun getById(id: Any): User? {
        val sql = "SELECT * FROM USERS WHERE dni = ?"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    User(
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

    override fun getAll(): MutableList<User> {
        val sql = "SELECT * FROM USERS"
        return dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                val users = mutableListOf<User>()
                while (rs.next()) {
                    users.add(
                        User(
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

    override fun update(entity: User): User {
        val sql = "UPDATE USERS SET name = ?, phone = ?, mail = ? WHERE dni = ?"
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
        val sql = "DELETE FROM USERS WHERE dni = ?"
        dataSource.connection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                stmt.executeUpdate()
            }
        }
    }
}
