package com.example.medicamentosubs.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UBSServiceAPI {

    companion object {
        const val BASE_URL =
            "https://apidadosabertos.saude.gov.br/"
    }


    @GET("assistencia-a-saude/unidade-basicas-de-saude")
    fun getUBSs(
        @Query("limit") limit: Int = 2000,
        @Query("offset") offset: Int = 0
    ): Call<APIResponse>
}