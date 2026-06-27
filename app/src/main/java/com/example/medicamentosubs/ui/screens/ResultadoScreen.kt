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
fun ResultadoScreen(
    navController: NavController
) {

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        items(Repositorio.ubs) { ubs ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = ubs.nome,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(ubs.endereco)

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            navController.navigate("detalhe")
                        }
                    ) {
                        Text("Ver detalhes")
                    }

                }

            }

        }

    }

}