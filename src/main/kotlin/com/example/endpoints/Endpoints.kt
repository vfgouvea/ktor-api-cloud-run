package com.example.endpoints

import com.example.models.Usuario
import com.example.services.UsuarioService
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val usuarioService by inject<UsuarioService>()
    val gson = Gson()

    routing {
        get("/") {
            call.respondText("Hello World!!!")
        }
        post("/"){
           try {
               val rawBodyContent = call.receive<String>()
               val usuario = gson.fromJson(rawBodyContent,Usuario::class.java)
               val usuarioId = usuarioService.create(usuario);
               call.respond(HttpStatusCode.OK, "Hello, usuario $usuarioId")
           } catch (error : Exception) {
               error.printStackTrace()
               call.respond("${error.cause?.message}")
           }
        }
    }
}
