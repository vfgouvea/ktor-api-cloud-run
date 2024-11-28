package com.example.repositories

import com.example.database.entities.Usuarios
import com.example.database.entities.toUsuarios
import com.example.models.Usuario
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId

class UsuarioRepository {

    fun create(usuario: Usuario) : String {
        return Usuarios.insert {
            it[id] = usuario.id
            it[nome] = usuario.nome
            it[email] = usuario.email
        } get Usuarios.id
    }

    fun select(usuarioId: String) : Usuario {
        return Usuarios.select(Usuarios.id eq usuarioId).toUsuarios().first()
    }

}