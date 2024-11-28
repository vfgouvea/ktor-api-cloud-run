package com.example

import com.example.database.DatabaseBuilder
import com.example.repositories.UsuarioRepository
import com.example.services.UsuarioService
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

val modules = module {
    single { DatabaseBuilder() }
    single { UsuarioRepository() }
    single { UsuarioService(get(), get()) }
}

fun initKoin() {
    startKoin {
        printLogger(Level.INFO)
        modules(modules)
    }
}