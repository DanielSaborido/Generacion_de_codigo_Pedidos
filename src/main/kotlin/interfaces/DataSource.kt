package `interfaces`

import java.sql.Connection

interface DataSource {
    fun getConnection() : Connection
}