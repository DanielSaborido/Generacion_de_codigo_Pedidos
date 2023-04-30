package interfaces

import entities.Client

interface Client {
    fun create(user: Client):Client?
    fun getAll(): List<Client>
    fun getById(dni: String): Client?
    fun update(user: Client):Client
    fun delete(dni: String)
    fun delete(user: Client):Client?
}