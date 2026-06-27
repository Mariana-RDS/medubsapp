package com.example.medicamentosubs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetalheUBSScreen(
    navController: NavController
){

    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        Text(
            "UBS Centro",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Medicamento pesquisado: Dipirona")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Encontrei o medicamento")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Não encontrei")
        }

        Button(
            onClick = {
                navController.navigate("historico")
            }
        ) {
            Text("Ver histórico")
        }

    }

}