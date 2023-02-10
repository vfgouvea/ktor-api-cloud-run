package com.example.plugins

import com.example.models.TestModel
import com.google.gson.Gson
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/"){
            val rawBodyContent = call.receive<String>()
            val testModel = Gson().fromJson(rawBodyContent,TestModel::class.java)
            call.respond("Hello, cliente with id: ${testModel.clienteId}")
        }
    }
}
