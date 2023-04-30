import interfaces.DataSource
import java.sql.Connection
import java.sql.DriverManager

class DataSourceImpl : DataSource {
    private val jdbcUrl = "jdbc:h2:C:\\Users\\sabor\\Downloads\\Generacion_de_codigo_Pedidos\\basededatos"
    override fun connection() : Connection {
        return DriverManager.getConnection(jdbcUrl, "daniel", "saborido")
    }
}