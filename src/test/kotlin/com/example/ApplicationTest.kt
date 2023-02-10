package com.example

import com.example.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {

        application {
            configureRouting()
        }
        environment{
            config = MapApplicationConfig(
                "ktor.deployment.host" to "0.0.0.0",
                "ktor.deployment.port" to "8080"
            )
        }
        var response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello World!", response.bodyAsText())
    }


}
