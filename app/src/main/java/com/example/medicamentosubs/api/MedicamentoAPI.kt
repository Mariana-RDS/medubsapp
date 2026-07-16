package com.example.medicamentosubs.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MedicamentoAPI {

    @GET("daf/estoque-medicamentos-bnafar-horus")
    fun buscarMedicamentos(
        @Query("codigo_uf") uf: Int = 26,
        @Query("limit") limit: Int = 2000,
        @Query("offset") offset: Int = 0
    ): Call<MedicamentoResponse>
}