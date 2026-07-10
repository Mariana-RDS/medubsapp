package com.example.medicamentosubs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.data.Repositorio
import com.example.medicamentosubs.model.Historico
import com.example.medicamentosubs.ui.theme.*

@Composable
fun DetalheUBSScreen(navController: NavController,
                     nomeUBS: String,
                     medicamento: String) {

    var mostrarDialog by remember { mutableStateOf(false) }
    var mensagemDialog by remember { mutableStateOf("") }

    fun registrar(encontrou: Boolean) {

        Repositorio.adicionarHistorico(
            Historico(
                usuario = "Usuário",
                medicamento = "Dipirona",
                ubs = "UBS Centro",
                encontrou = encontrou,
                data = "30/06/2026"
            )
        )

        mensagemDialog = if (encontrou)
            "Obrigado! Informação registrada"
        else
            "Obrigado! Sua informação ajuda outros usuários"

        mostrarDialog = true
    }

    MainLayout(
        title = "Detalhes da UBS",
        navController = navController
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEAF2FF)
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = nomeUBS,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Preto
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Medicamento: $medicamento",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Preto
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        color = Color(0xFFDCEBFF),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Status baseado em relatos de usuários",
                            color = Preto,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }

            Button(
                onClick = { registrar(true) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4B953D),
                    contentColor = Branco
                )
            ) {
                Text("✔ Encontrei o medicamento")
            }

            Button(
                onClick = { registrar(false) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AmareloGov,
                    contentColor = Branco
                )
            ) {
                Text("✖ Não encontrei")
            }

            OutlinedButton(
                onClick = {
                    navController.navigate("historico")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AzulGov
                )
            ) {
                Text("Ver histórico")
            }
        }

        if (mostrarDialog) {

            AlertDialog(
                onDismissRequest = { mostrarDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            mostrarDialog = false
                            navController.popBackStack()
                        }
                    ) {
                        Text("OK")
                    }
                },
                title = {
                    Text("Registro concluído")
                },
                text = {
                    Text(mensagemDialog)
                }
            )
        }
    }
}