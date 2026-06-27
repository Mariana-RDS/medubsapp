package com.example.medicamentosubs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController
) {

    var medicamento by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Medicamento UBS",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = medicamento,
            onValueChange = {
                medicamento = it
            },
            label = {
                Text("Pesquisar medicamento")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("resultado")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pesquisar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = {
                navController.navigate("resultado")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar UBS próximas")
        }
    }
}