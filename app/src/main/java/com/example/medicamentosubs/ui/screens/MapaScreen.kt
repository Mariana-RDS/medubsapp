package com.example.medicamentosubs.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.api.UBSService
import com.example.medicamentosubs.data.Repositorio
import com.example.medicamentosubs.model.UBS
import com.example.medicamentosubs.model.Historico
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapaScreen(navController: NavController) {

    val context = LocalContext.current

    var minhaLatitude by remember { mutableStateOf(-8.0476) }
    var minhaLongitude by remember { mutableStateOf(-34.8770) }

    val service = remember { UBSService() }

    var ubsList by remember { mutableStateOf(listOf<UBS>()) }

    var ubsSelecionada by remember { mutableStateOf<UBS?>(null) }
    var historicoUBS by remember { mutableStateOf<Map<String, List<Historico>>>(emptyMap()) }

    val locationPermission =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { permitido ->

            if (permitido) {
                val client = LocationServices.getFusedLocationProviderClient(context)

                client.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        minhaLatitude = location.latitude
                        minhaLongitude = location.longitude
                    }
                }
            }
        }

    LaunchedEffect(Unit) {

        val permitido =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (permitido) {
            val client = LocationServices.getFusedLocationProviderClient(context)

            client.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    minhaLatitude = location.latitude
                    minhaLongitude = location.longitude
                }
            }
        } else {
            locationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        service.getUBSs {
            ubsList = it
        }
    }

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(minhaLatitude, minhaLongitude) {
        cameraPositionState.animate(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(minhaLatitude, minhaLongitude),
                14f
            )
        )
    }

    MainLayout(
        title = "Mapa de UBS",
        navController = navController
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled =
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                ),
                uiSettings = MapUiSettings(
                    myLocationButtonEnabled = true
                )
            ) {

                ubsList.forEach { ubs ->

                    if (ubs.latitude != 0.0 && ubs.longitude != 0.0) {

                        Marker(
                            state = MarkerState(
                                position = LatLng(ubs.latitude, ubs.longitude)
                            ),
                            title = ubs.nome,
                            snippet = ubs.endereco,
                            onClick = {

                                ubsSelecionada = ubs

                                historicoUBS = Repositorio.historico
                                    .filter { it.ubs == ubs.nome }
                                    .groupBy { it.medicamento }

                                true
                            }
                        )
                    }
                }
            }

            if (ubsSelecionada != null) {

                val ubs = ubsSelecionada!!

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(androidx.compose.ui.Alignment.BottomCenter),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        Text(
                            text = ubs.nome,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = ubs.endereco,
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Medicamentos encontrados:",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        if (historicoUBS.isEmpty()) {
                            Text(
                                text = "Nenhum dado ainda",
                                color = Color.Gray
                            )
                        }

                        historicoUBS.forEach { (medicamento, lista) ->

                            val encontrados = lista.count { it.encontrou }
                            val naoEncontrados = lista.count { !it.encontrou }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(medicamento)

                                Row {

                                    Text(
                                        text = "✅ $encontrados",
                                        color = Color(0xFF2E7D32)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = "❌ $naoEncontrados",
                                        color = Color(0xFFC62828)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        TextButton(
                            onClick = { ubsSelecionada = null }
                        ) {
                            Text("Fechar")
                        }
                    }
                }
            }
        }
    }
}