package com.example.medicamentosubs.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout
import com.example.medicamentosubs.api.UBSService
import com.example.medicamentosubs.model.UBS
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@Composable
fun MapaScreen(navController: NavController) {

    val context = LocalContext.current


    var minhaLatitude by remember {
        mutableStateOf(-8.0476)
    }

    var minhaLongitude by remember {
        mutableStateOf(-34.8770)
    }


    val service = remember {
        UBSService()
    }


    var ubsList by remember {
        mutableStateOf(listOf<UBS>())
    }


    val locationPermission =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { permitido ->


            if (permitido) {


                val client =
                    LocationServices
                        .getFusedLocationProviderClient(context)


                client.lastLocation
                    .addOnSuccessListener { location ->


                        if (location != null) {

                            minhaLatitude =
                                location.latitude

                            minhaLongitude =
                                location.longitude


                            println(
                                "LOCALIZAÇÃO: ${location.latitude}, ${location.longitude}"
                            )

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


            val client =
                LocationServices
                    .getFusedLocationProviderClient(context)


            client.lastLocation
                .addOnSuccessListener { location ->


                    if (location != null) {

                        minhaLatitude =
                            location.latitude

                        minhaLongitude =
                            location.longitude


                        println(
                            "LOCALIZAÇÃO: ${location.latitude}, ${location.longitude}"
                        )

                    }

                }


        } else {


            locationPermission.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )

        }


        service.getUBSs {

            println(
                "TOTAL UBS: ${it.size}"
            )

            ubsList = it

        }

    }


    val cameraPositionState =
        rememberCameraPositionState()


    LaunchedEffect(
        minhaLatitude,
        minhaLongitude
    ) {


        cameraPositionState.animate(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    minhaLatitude,
                    minhaLongitude
                ),
                14f
            )
        )

    }




    MainLayout(
        title = "Mapa de UBS",
        navController = navController
    ) {


        GoogleMap(

            modifier = Modifier.fillMaxSize(),

            cameraPositionState =
                cameraPositionState,


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



                if (
                    ubs.latitude != 0.0 &&
                    ubs.longitude != 0.0
                ) {


                    Marker(

                        state = MarkerState(

                            position =
                                LatLng(
                                    ubs.latitude,
                                    ubs.longitude
                                )

                        ),


                        title = ubs.nome,


                        snippet =
                            ubs.endereco,



                        onClick = {


                            navController.navigate(

                                "detalheMapa/" +

                                        "${Uri.encode(ubs.nome)}/" +

                                        "${Uri.encode(ubs.endereco)}/" +

                                        "${Uri.encode(ubs.bairro)}/" +

                                        "${ubs.latitude}/" +

                                        "${ubs.longitude}"

                            )


                            false

                        }

                    )

                }

            }

        }

    }

}