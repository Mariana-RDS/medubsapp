package com.example.medicamentosubs.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.model.UBS
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.*

@Composable
fun MapaScreen(navController: NavController) {

    val ubsList = listOf(
        UBS("UBS Boa Vista", -8.0578, -34.8829, "Boa Vista - Recife"),
        UBS("UBS Casa Amarela", -8.0276, -34.9210, "Casa Amarela - Recife"),
        UBS("UBS Imbiribeira", -8.1082, -34.9123, "Imbiribeira - Recife")
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(-8.0476, -34.8770), 12f
        )
    }

    MainLayout(
        title = "UBS próximas",
        navController = navController
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {

            ubsList.forEach { ubs ->
                Marker(
                    state = MarkerState(
                        position = LatLng(ubs.latitude, ubs.longitude)
                    ),
                    title = ubs.nome,
                    snippet = ubs.endereco
                )
            }
        }
    }
}