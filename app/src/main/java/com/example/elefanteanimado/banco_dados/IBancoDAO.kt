package com.example.elefanteanimado.banco_dados

interface IBancoDAO {

    fun salvar()
    fun atualizar(posicaoAtu: String)
    fun ler() : String
}