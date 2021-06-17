package com.example.elefanteanimado.banco_dados

import android.content.Context
import android.content.ContextWrapper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File


class BancoDeDados(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DB_POSICAO"
        const val TABELA_POSICAO = "posicoes04"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "CREATE TABLE IF NOT EXISTS " + TABELA_POSICAO + " (id INTEGER PRIMARY KEY AUTOINCREMENT, posicao TEXT NOT NULL ); "
        try {
            db?.execSQL(sql)
            Log.v("SUCESSO", "Sucesso ao criar tabela")
        } catch (e: Exception) {
            Log.v("ERRO", "Erro ao criar tabela: " + e.message.toString())
        }
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun doesDatabaseExist(context: ContextWrapper, dbName: String?): Boolean {
        val dbFile: File = context.getDatabasePath(dbName)
        return dbFile.exists()
    }
}