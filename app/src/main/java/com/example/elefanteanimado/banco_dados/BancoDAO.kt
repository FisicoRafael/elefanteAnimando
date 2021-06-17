package com.example.elefanteanimado.banco_dados

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import java.lang.Exception

class BancoDAO(context: Context) : IBancoDAO {

    private val db = BancoDeDados(context)

    private var escrever = db.writableDatabase
    private var le = db.readableDatabase

    override fun salvar() {
        val cv = ContentValues()
        cv.apply {
            put("posicao", "01")
        }
        try {
            escrever.insert(BancoDeDados.TABELA_POSICAO, null, cv)
            Log.v("SUCESSO", "Posicao salva com sucesso")
        } catch (e: Exception) {
            Log.v("ERRO", "Erro ao salvar a posicao: " + e.message.toString())

        }

    }

    override fun atualizar(posicaoAtu: String) {
        val cv = ContentValues()
        cv.apply {
            put("posicao", posicaoAtu)
        }
        try {
            val args = arrayOf("1")
            escrever.update(BancoDeDados.TABELA_POSICAO, cv, "id=?", args)
            Log.v("SUCESSO", "Posicao Atualizada com sucesso")
        } catch (e: Exception) {
            Log.v("ERRO", "Erro ao atualizar a posicao: " + e.message.toString())

        }

    }


    @SuppressLint("Recycle")
    override fun ler(): String {
        val sql: String = "SELECT * FROM " + BancoDeDados.TABELA_POSICAO + " ;"
        val cursor: Cursor = le.rawQuery(sql, null)

        cursor.moveToFirst()
        val id: Int = cursor.getInt(cursor.getColumnIndex("id"))
        val posicao: String = cursor.getString(cursor.getColumnIndex("posicao"))


        Log.v("PosicaoInicial", "posicaoIni: $posicao")
        Log.v("PosicaoInicial", "posicaoIni: $id")
        return posicao
    }


}