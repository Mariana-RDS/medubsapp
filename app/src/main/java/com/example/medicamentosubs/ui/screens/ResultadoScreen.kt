package com.example.medicamentosubs.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.data.Repositorio
import com.example.medicamentosubs.ui.theme.AmareloGov
import com.example.medicamentosubs.ui.theme.AzulGov
import com.example.medicamentosubs.ui.theme.Branco
import com.example.medicamentosubs.ui.theme.Preto

@Composable
fun ResultadoScreen(
    navController: NavController,
    medicamento: String
) {

    val lista = Repositorio.ubs

    MainLayout(title = "Resultado: $medicamento",
        navController = navController) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(lista) { ubs ->

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Branco),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = ubs.nome,
                            color = Preto,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = ubs.endereco,
                            color = Preto,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedButton(
                            onClick = { navController.navigate(
                                "detalhe/${Uri.encode(ubs.nome)}/${Uri.encode(medicamento)}"
                            ) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            border = null,
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = AmareloGov,
                                contentColor = Branco
                            )
                        ) {
                            Text("Ver detalhe")
                        }
                    }
                }
            }
        }
    }

}