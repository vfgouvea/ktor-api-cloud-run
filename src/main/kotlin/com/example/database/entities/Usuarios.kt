package com.example.database.entities

import com.example.models.Usuario
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Usuarios: Table(name = "Usuarios"){
    val id = varchar("id", 50)
    val nome = varchar("nome", 255)
    val email = varchar("email", 255)

    override val primaryKey = PrimaryKey(id)
}

fun Iterable<ResultRow>.toUsuarios(): List<Usuario> {
    return this.map { it.toUsuario() }
}

fun ResultRow.toUsuario(): Usuario {
    return Usuario(
        id = this[Usuarios.id],
        nome = this[Usuarios.nome],
        email = this[Usuarios.email]
    )
}