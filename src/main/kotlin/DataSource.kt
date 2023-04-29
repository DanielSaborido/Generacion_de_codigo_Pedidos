import interfaces.DataSource
import java.sql.Connection
import java.sql.DriverManager

class DataSourceImpl : DataSource {
    private val jdbcUrl = "jdbc:h2:~/test"
    override fun getConnection() : Connection {
        return DriverManager.getConnection(jdbcUrl, "as", "")
    }
}