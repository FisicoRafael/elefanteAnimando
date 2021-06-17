package com.example.elefanteanimado.requisicao

import com.example.elefanteanimado.model.TextoRequisitado
import retrofit2.Call
import retrofit2.http.GET

interface ITextoReq {

    @GET("facts")
    fun list() : Call<List<TextoRequisitado>>

}