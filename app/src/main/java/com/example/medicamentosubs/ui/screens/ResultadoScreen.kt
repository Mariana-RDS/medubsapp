package com.example.medicamentosubs.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.api.MedicamentoService
import com.example.medicamentosubs.api.UBSService
import com.example.medicamentosubs.model.UBS
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun ResultadoScreen(
    navController: NavController,
    medicamento: String
) {

    val context = LocalContext.current

    val service = remember { MedicamentoService() }
    val ubsService = remember { UBSService() }

    var lista by remember { mutableStateOf(listOf<UBS>()) }

    var ubsSelecionada by remember { mutableStateOf<UBS?>(null) }
    var mostrarDialog by remember { mutableStateOf(false) }

    var minhaLatitude by remember { mutableStateOf(-8.0476) }
    var minhaLongitude by remember { mutableStateOf(-34.8770) }

    val cameraPositionState = rememberCameraPositionState()

    val locationPermission =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { permitido ->
            if (permitido) pegarLocalizacao(context) { lat, lon ->
                minhaLatitude = lat
                minhaLongitude = lon
                carregar(ubsService, service, medicamento, lat, lon) { lista = it }
            }
        }

    LaunchedEffect(Unit) {

        val permitido =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (permitido) {
            pegarLocalizacao(context) { lat, lon ->
                minhaLatitude = lat
                minhaLongitude = lon
                carregar(ubsService, service, medicamento, lat, lon) { lista = it }
            }
        } else {
            locationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    LaunchedEffect(minhaLatitude, minhaLongitude) {
        cameraPositionState.animate(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(minhaLatitude, minhaLongitude),
                13f
            )
        )
    }

    MainLayout(
        title = "Mapa: $medicamento",
        navController = navController
    ) {

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

            lista.forEach { ubs ->

                Marker(
                    state = MarkerState(
                        position = LatLng(ubs.latitude, ubs.longitude)
                    ),
                    title = ubs.nome,
                    snippet = ubs.endereco,
                    onClick = {
                        ubsSelecionada = ubs
                        mostrarDialog = true
                        true
                    }
                )
            }
        }

        if (mostrarDialog && ubsSelecionada != null) {

            val ubs = ubsSelecionada!!

            AlertDialog(
                onDismissRequest = { mostrarDialog = false },
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp,
                title = {
                    Text(
                        text = ubs.nome,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                text = {
                    Column {
                        Text(
                            text = ubs.endereco,
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Tem $medicamento nesta UBS?",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                },
                confirmButton = {

                    Button(
                        onClick = {
                            registrarHistorico(ubs.nome, medicamento, true)
                            mostrarDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(0xFF4CAF50)
                        )
                    ) {
                        Text("✔ Tem")
                    }
                },
                dismissButton = {

                    Button(
                        onClick = {
                            registrarHistorico(ubs.nome, medicamento, false)
                            mostrarDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(0xFFFFC107)
                        )
                    ) {
                        Text("✖ Não tem")
                    }
                }
            )
        }
    }
}


fun pegarLocalizacao(
    context: android.content.Context,
    onResult: (Double, Double) -> Unit
) {

    val permitido =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    if (!permitido) return

    val client = LocationServices.getFusedLocationProviderClient(context)

    try {
        client.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                onResult(location.latitude, location.longitude)
            }
        }
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}

fun distanciaKm(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double
): Double {

    val resultado = FloatArray(1)

    Location.distanceBetween(
        lat1, lon1,
        lat2, lon2,
        resultado
    )

    return resultado[0].toDouble() / 1000
}

fun carregar(
    ubsService: UBSService,
    service: MedicamentoService,
    medicamento: String,
    lat: Double,
    lon: Double,
    onResult: (List<UBS>) -> Unit
) {
    ubsService.getUBSs { ubs ->

        service.buscarPorNome(medicamento, ubs) { filtradas ->

            val ordenadas = filtradas
                .filter { it.latitude != 0.0 && it.longitude != 0.0 }
                .sortedBy {
                    distanciaKm(lat, lon, it.latitude, it.longitude)
                }
                .take(20)

            onResult(ordenadas)
        }
    }
}