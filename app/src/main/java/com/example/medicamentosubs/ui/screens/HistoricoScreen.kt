package com.example.medicamentosubs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.data.Repositorio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.db.fb.FBDatabase
import com.example.medicamentosubs.ui.theme.Branco
import com.example.medicamentosubs.ui.theme.Preto

@Composable
fun HistoricoScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        FBDatabase().loadHistorico()
    }

    MainLayout(title = "Histórico",
        navController = navController) {

        val historicoAgrupado = Repositorio.historico.groupBy { it.ubs }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            historicoAgrupado.forEach { (ubsNome, registros) ->

                item {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Branco),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {

                        Column(modifier = Modifier.padding(12.dp)) {

                            Text(
                                text = ubsNome,
                                style = MaterialTheme.typography.titleLarge,
                                color = Preto
                            )

                            Spacer(modifier = Modifier.height(6.dp))


                            Spacer(modifier = Modifier.height(12.dp))

                            val medicamentosAgrupados = registros.groupBy { it.medicamento }

                            medicamentosAgrupados.forEach { (medicamento, listaMed) ->

                                val encontrados = listaMed.count { it.encontrou }
                                val naoEncontrados = listaMed.count { !it.encontrou }

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
                                    elevation = CardDefaults.cardElevation(1.dp)
                                ) {

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Column {

                                            Text(
                                                text = medicamento,
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Preto
                                            )

                                            Spacer(modifier = Modifier.height(4.dp))

                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {

                                                Surface(
                                                    color = Color(0xFFE8F5E9),
                                                    shape = RoundedCornerShape(6.dp)
                                                ) {
                                                    Text(
                                                        text = "$encontrados encontraram",
                                                        color = Color(0xFF2E7D32),
                                                        modifier = Modifier.padding(4.dp)
                                                    )
                                                }

                                                Surface(
                                                    color = Color(0xFFFFEBEE),
                                                    shape = RoundedCornerShape(6.dp)
                                                ) {
                                                    Text(
                                                        text = "$naoEncontrados não encontraram",
                                                        color = Color(0xFFC62828),
                                                        modifier = Modifier.padding(4.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}