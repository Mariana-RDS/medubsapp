package com.example.medicamentosubs.data


import com.example.medicamentosubs.model.Historico
import com.example.medicamentosubs.model.UBS

object Repositorio{

    val ubs = listOf(

        UBS(
            nome = "UBS Centro",
            endereco = "Rua A, 120",
            latitude = 0.0,
            longitude = 0.0,
            medicamentos = listOf(
                "Dipirona",
                "Paracetamol",
                "Amoxicilina"
            )
        ),

        UBS(
            nome = "UBS Jardim",
            endereco = "Rua B, 450",
            latitude = 0.0,
            longitude = 0.0,
            medicamentos = listOf(
                "Ibuprofeno",
                "Dipirona"
            )
        )
    )

    val historico = listOf(

        Historico(
            usuario = "Maria",
            medicamento = "Dipirona",
            ubs = "UBS Centro",
            encontrou = true,
            data = "20/06/2026"
        ),

        Historico(
            usuario = "Carlos",
            medicamento = "Dipirona",
            ubs = "UBS Centro",
            encontrou = false,
            data = "22/06/2026"
        )
    )
}