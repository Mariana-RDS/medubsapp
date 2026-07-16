package com.example.medicamentosubs.data


import androidx.compose.runtime.mutableStateListOf
import com.example.medicamentosubs.model.Historico
import com.example.medicamentosubs.model.UBS

object Repositorio {

    val historico = mutableStateListOf<Historico>()

    fun adicionarHistorico(item: Historico) {
        historico.add(0, item)
    }
}