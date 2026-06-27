package com.example.medicamentosubs.model

data class UBS(

    val nome:String="",

    val endereco:String="",

    val latitude:Double=0.0,

    val longitude:Double=0.0,

    val medicamentos:List<String> = emptyList()
)
