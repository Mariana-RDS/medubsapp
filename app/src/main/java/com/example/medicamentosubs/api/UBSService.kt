package com.example.medicamentosubs.api

import com.example.medicamentosubs.model.UBS
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class UBSService {

    private val api: UBSServiceAPI

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(UBSServiceAPI.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()


        api = retrofit.create(
            UBSServiceAPI::class.java
        )
    }


    fun getUBSs(
        onResult: (List<UBS>) -> Unit
    ) {

        val call = api.getUBSs()


        call.enqueue(object : Callback<APIResponse> {


            override fun onResponse(
                call: Call<APIResponse>,
                response: Response<APIResponse>
            ) {


                println(
                    "SUCESSO API: ${response.code()}"
                )


                val lista =
                    response.body()?.ubs?.map {


                        val lat =
                            it.latitude
                                ?.replace(",", ".")
                                ?.toDoubleOrNull()
                                ?: 0.0


                        val lng =
                            it.longitude
                                ?.replace(",", ".")
                                ?.toDoubleOrNull()
                                ?: 0.0



                        UBS(

                            nome = it.nome ?: "Sem nome",

                            endereco = it.logradouro ?: "",

                            bairro = it.bairro ?: "",

                            latitude = lat,

                            longitude = lng,
                            cnes = it.cnes

                        )


                    } ?: emptyList()



                println(
                    "TOTAL UBS: ${lista.size}"
                )


                onResult(lista)

            }



            override fun onFailure(
                call: Call<APIResponse>,
                t: Throwable
            ) {

                println(
                    "ERRO API:"
                )

                t.printStackTrace()

                onResult(emptyList())

            }

        })

    }
}