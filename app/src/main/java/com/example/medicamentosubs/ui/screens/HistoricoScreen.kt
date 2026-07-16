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
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Branco),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = item.usuario,
                                color = Preto,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "${item.data}",
                                color = Preto,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Text(
                            text = "Medicamento: ${item.medicamento}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Preto
                        )

                        Text(
                            text = "UBS: ${item.ubs}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Preto
                        )

                        Surface(
                            color = if (item.encontrou)
                                Color(0xFFE8F5E9)
                            else
                                Color(0xFFFFEBEE),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (item.encontrou)
                                    "✅ Encontrado pelo usuário"
                                else
                                    "❌ Não encontrado",
                                color = if (item.encontrou)
                                    Color(0xFF2E7D32)
                                else
                                    Color(0xFFC62828),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}