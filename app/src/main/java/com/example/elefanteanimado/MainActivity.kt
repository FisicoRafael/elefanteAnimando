package com.example.elefanteanimado


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.appcompat.app.AlertDialog


import com.airbnb.lottie.LottieAnimationView
import com.example.elefanteanimado.banco_dados.BancoDAO
import com.example.elefanteanimado.banco_dados.BancoDeDados
import com.example.elefanteanimado.model.TextoRequisitado
import com.example.elefanteanimado.requisicao.ITextoReq
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var elefante01: LottieAnimationView
    private lateinit var elefante02: LottieAnimationView
    private lateinit var elefante03: LottieAnimationView
    private lateinit var elefante04: LottieAnimationView
    private lateinit var elefante05: LottieAnimationView

    private lateinit var posicaoElefante: BancoDAO
    private lateinit var retrofit: Retrofit



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        inicializarComponentes()
        esconderEledantes()
        posicaoElefante = BancoDAO(this)
        if (!BancoDeDados(this).doesDatabaseExist(this, "posicoes04")) {
            BancoDAO(this).salvar()
        }
        posicaoInicial(posicaoElefante.ler())

        retrofit = Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

    fun chamarAlertDialo(text: String) {
        val construir = AlertDialog.Builder(this)
        construir.setMessage(text)
        construir.setPositiveButton("OK") { _, _ -> }
        val dialog: AlertDialog = construir.create()
        dialog.show()
    }

    private fun recuperarTexto(index: Int) {
        val iTextoReq: ITextoReq = retrofit.create(ITextoReq::class.java)
        val call = iTextoReq.list()

        call.enqueue(object : Callback<List<TextoRequisitado>> {

            override fun onResponse(
                call: Call<List<TextoRequisitado>>,
                response: Response<List<TextoRequisitado>>
            ) {
                response.body()?.let {
                    val lista: List<TextoRequisitado> = it
//                    Log.v("REQ", "Texto: " + lista[index].text)
                    chamarAlertDialo(lista[index].text)

                }

            }

            override fun onFailure(call: Call<List<TextoRequisitado>>, t: Throwable) {
                t.message?.let { Log.e("onFailure error", it) }
            }

        })
    }


    private fun posicaoInicial(lerPosicao: String) {
        esconderEledantes()
        when (lerPosicao) {
            "01" -> elefante01.visibility = View.VISIBLE
            "02" -> elefante02.visibility = View.VISIBLE
            "03" -> elefante03.visibility = View.VISIBLE
            "04" -> elefante04.visibility = View.VISIBLE
            "05" -> elefante05.visibility = View.VISIBLE
        }
    }


    private fun inicializarComponentes() {
        elefante01 = findViewById(R.id.elefante01)
        elefante02 = findViewById(R.id.elefante02)
        elefante03 = findViewById(R.id.elefante03)
        elefante04 = findViewById(R.id.elefante04)
        elefante05 = findViewById(R.id.elefante05)
    }

    private fun esconderEledantes() {
        elefante01.visibility = View.GONE
        elefante02.visibility = View.GONE
        elefante03.visibility = View.GONE
        elefante04.visibility = View.GONE
        elefante05.visibility = View.GONE
    }

    fun escolher(view: View) {
        esconderEledantes()
        when (view.id) {
            R.id.step01 -> {
                posicaoElefante.atualizar("01")
                elefante01.visibility = View.VISIBLE
                recuperarTexto(0)
            }
            R.id.step02 -> {
                posicaoElefante.atualizar("02")
                elefante02.visibility = View.VISIBLE
                recuperarTexto(1)
            }
            R.id.step03 -> {
                posicaoElefante.atualizar("03")
                elefante03.visibility = View.VISIBLE
                recuperarTexto(2)
            }
            R.id.step04 -> {
                posicaoElefante.atualizar("04")
                elefante04.visibility = View.VISIBLE
                recuperarTexto(3)
            }
            R.id.step05 -> {
                posicaoElefante.atualizar("05")
                elefante05.visibility = View.VISIBLE
                recuperarTexto(4)
            }
        }

    }


}

















