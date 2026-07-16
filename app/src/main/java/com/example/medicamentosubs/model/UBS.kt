package com.example.medicamentosubs.model

data class UBS(

    val nome: String = "",

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val endereco: String = "",

    val bairro: String = "",

    val medicamentos: List<String> = emptyList(),

    val possuiMedicamento: Boolean = false,

    val cnes: String? = null
)