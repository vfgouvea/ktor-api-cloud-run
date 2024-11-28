package com.example.database

import com.example.database.entities.Usuarios
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
class DatabaseBuilder {

   var database: Database

   init {
       try {

           // LOCAL
          /* database = Database.connect(
               url = "jdbc:sqlite:database.sqlite",
               driver = "org.sqlite.JDBC"
           )*/

           // CloudSQL
           database = Database.connect(
               url = System.getenv("DATABASE_URL"),
               driver = "org.postgresql.Driver",
               user = System.getenv("DATABASE_USER"),
               password = System.getenv("DATABASE_PASSWORD")
           )
           transaction(database) {
               SchemaUtils.createMissingTablesAndColumns(
                   Usuarios
               )
           }
       } catch (error : Exception){
           throw DatabaseBuilderException("Problema ao conectar com banco: ${error.message}")
       }
   }

}