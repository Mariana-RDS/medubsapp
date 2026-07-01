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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.ui.theme.Branco
import com.example.medicamentosubs.ui.theme.Preto

@Composable
fun HistoricoScreen(navController: NavController) {

    MainLayout(title = "Histórico",
        navController = navController) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(Repositorio.historico) { item ->

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Branco),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = item.usuario,
                            color = Preto,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = item.data,
                            color = Preto,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text("Medicamento: ${item.medicamento}")
                        Text("UBS: ${item.ubs}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Surface(
                            color = if (item.encontrou)
                                Color(0xFFE8F5E9)
                            else
                                Color(0xFFFFEBEE),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = if (item.encontrou)
                                    "Disponível"
                                else
                                    "Indisponível",
                                color = if (item.encontrou)
                                    Color(0xFF2E7D32)
                                else
                                    Color(0xFFC62828),
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}