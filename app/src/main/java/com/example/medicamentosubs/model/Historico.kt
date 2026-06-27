package com.example.medicamentosubs.model
data class Historico(
    val usuario: String,
    val medicamento: String,
    val ubs: String,
    val encontrou: Boolean,
    val data: String
)