package com.example.services

import com.example.database.DatabaseBuilder
import com.example.models.Usuario
import com.example.repositories.UsuarioRepository
import org.jetbrains.exposed.sql.transactions.transaction

class UsuarioService(private val repository: UsuarioRepository, private val db: DatabaseBuilder) {

    fun create(usuario: Usuario) : String = transaction(db = db.database) {
        repository.create(usuario)
    }

    fun select(usuarioId: String) : Usuario = transaction(db = db.database) {
        repository.select(usuarioId)
    }

}