package com.example.medicamentosubs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.data.Repositorio

@Composable
fun HistoricoScreen(
    navController: NavController
) {

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        items(Repositorio.historico) { item ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(item.usuario)

                    Text(item.data)

                    Text(item.medicamento)

                    Text(item.ubs)

                    Text(
                        if (item.encontrou)
                            "✅ Encontrou"
                        else
                            "❌ Não encontrou"
                    )

                }

            }

        }

    }

}