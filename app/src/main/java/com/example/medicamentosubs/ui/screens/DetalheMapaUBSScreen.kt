package com.example.medicamentosubs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.ui.theme.*

@Composable
fun DetalheMapaUBSScreen(
    navController: NavController,
    nome: String,
    endereco: String,
    bairro: String,
    latitude: String,
    longitude: String
) {


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
                        text = nome,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Preto
                    )


                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "Endereço",
                        style = MaterialTheme.typography.titleMedium,
                        color = AzulGov
                    )


                    Text(
                        text = endereco,
                        color = Preto
                    )


                    Spacer(modifier = Modifier.height(12.dp))


                    Text(
                        text = "Bairro",
                        style = MaterialTheme.typography.titleMedium,
                        color = AzulGov
                    )


                    Text(
                        text = bairro,
                        color = Preto
                    )


                    Spacer(modifier = Modifier.height(12.dp))


                    Text(
                        text = "Localização",
                        style = MaterialTheme.typography.titleMedium,
                        color = AzulGov
                    )


                    Text(
                        text = "$latitude, $longitude",
                        color = Preto
                    )

                }
            }



            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulGov,
                    contentColor = Branco
                )
            ) {

                Text("Voltar ao mapa")

            }

        }

    }
}