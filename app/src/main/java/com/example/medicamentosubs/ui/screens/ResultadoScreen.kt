package com.example.medicamentosubs.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.api.MedicamentoService
import com.example.medicamentosubs.api.UBSService
import com.example.medicamentosubs.data.Repositorio
import com.example.medicamentosubs.model.UBS
import com.example.medicamentosubs.ui.theme.AmareloGov
import com.example.medicamentosubs.ui.theme.Branco
import com.example.medicamentosubs.ui.theme.Preto
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun ResultadoScreen(
    navController: NavController,
    medicamento: String
) {

    val service = remember { MedicamentoService() }
    val ubsService = remember { UBSService() }

    var lista by remember { mutableStateOf(listOf<UBS>()) }

    val backStackEntry =
        navController.currentBackStackEntryAsState()


    fun carregarUBSs(){

        ubsService.getUBSs { ubs ->

            service.buscarPorNome(medicamento, ubs) {

                lista = it

            }

        }

    }

    LaunchedEffect(
        backStackEntry.value
    ) {

        carregarUBSs()

    }


    MainLayout(title = "Resultado: $medicamento",
        navController = navController) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(lista) { ubs ->

                val encontrado =
                    Repositorio.historico.any {

                        it.ubs == ubs.nome &&
                                it.medicamento == medicamento &&
                                it.encontrou

                    }

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
                        Text(
                            text =
                                when {
                                    encontrado ->
                                        "✅ Você encontrou nesta UBS"

                                    ubs.possuiMedicamento ->
                                        "✅ Medicamento disponível"

                                    else ->
                                        "❌ Sem estoque informado"
                                },

                            color =
                                if(encontrado || ubs.possuiMedicamento)
                                    Color(0xFF4B953D)
                                else
                                    Preto
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