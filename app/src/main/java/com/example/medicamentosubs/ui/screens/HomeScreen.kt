package com.example.medicamentosubs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {

    var medicamento by remember { mutableStateOf("") }

    val podePesquisar = medicamento.trim().isNotEmpty()

    MainLayout(title = "Medicamentos UBS",
        navController = navController) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Buscar medicamento",
                style = MaterialTheme.typography.titleLarge,
                color = Preto
            )

            OutlinedTextField(
                value = medicamento,
                onValueChange = { medicamento = it },
                label = { Text("Ex: Dipirona", color = Preto) },
                textStyle = LocalTextStyle.current.copy(color = Preto),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Preto,
                    unfocusedTextColor = Preto,
                    focusedBorderColor = AzulGov,
                    unfocusedBorderColor = AzulClaro,
                    cursorColor = AzulGov
                )
            )

            Button(
                onClick = {
                    if (podePesquisar) {
                        navController.navigate("resultado/${medicamento.trim()}")
                    }
                },
                enabled = podePesquisar,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulGov,
                    contentColor = Branco,
                    disabledContainerColor = AzulClaro,
                    disabledContentColor = Branco
                )
            ) {
                Text("Pesquisar")
            }


        }
    }

}
