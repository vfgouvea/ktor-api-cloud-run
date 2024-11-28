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
               url = "jdbc:postgresql://34.28.161.7:5432/ktordatabase",
               driver = "org.postgresql.Driver",
               user = "postgres",
               password = "postgres"
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

    /*
    try {
            database = Database.connect(
                url = "jdbc:postgresql://34.28.161.7:5432/ktordatabase",
                driver = "org.postgresql.Driver",
                user = "postgres",
                password = "postgres"
            )
            transaction(database) {
                SchemaUtils.createMissingTablesAndColumns(
                    Usuarios
                )
            }
        } catch (error : Exception){
            throw DatabaseBuilderException("Problema ao conectar com banco: ${error.message}")
        }
     */

}