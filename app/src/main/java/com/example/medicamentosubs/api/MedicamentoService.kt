package com.example.medicamentosubs.api

import com.example.medicamentosubs.model.UBS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MedicamentoService {

    private val api: MedicamentoAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apidadosabertos.saude.gov.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MedicamentoAPI::class.java)
    }

    fun buscarPorNome(
        nome: String,
        ubsList: List<UBS>,
        onResult: (List<UBS>) -> Unit
    ) {

        api.buscarMedicamentos().enqueue(object : Callback<MedicamentoResponse> {

            override fun onResponse(
                call: Call<MedicamentoResponse>,
                response: Response<MedicamentoResponse>
            ) {

                val medicamentos = response.body()?.parametros ?: emptyList()

                val nomeSimplificado = nome
                    .lowercase()
                    .trim()
                    .split(" ")
                    .firstOrNull() ?: nome

                println("BUSCANDO POR: $nomeSimplificado")

                val filtrados = medicamentos.filter {

                    val descricao = it.descricao_produto
                        ?.lowercase() ?: ""

                    descricao.contains(nomeSimplificado)
                }

                val cnesComMedicamento = filtrados.mapNotNull {
                    it.codigo_cnes
                }.toSet()

                val resultado = ubsList.map { ubs ->

                    val possui =
                        ubs.cnes?.toLongOrNull() in cnesComMedicamento


                    ubs.copy(
                        possuiMedicamento = possui
                    )
                }

                onResult(resultado)
            }

            override fun onFailure(
                call: Call<MedicamentoResponse>,
                t: Throwable
            ) {
                println("ERRO API MEDICAMENTO")
                t.printStackTrace()
                onResult(emptyList())
            }
        })
    }
}