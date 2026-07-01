package com.example.medicamentosubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.medicamentosubs.ui.theme.AzulGov
import com.example.medicamentosubs.ui.theme.Branco
import com.example.medicamentosubs.ui.theme.CinzaClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    title: String,
    navController: NavController,
    content: @Composable () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(title)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulGov,
                    titleContentColor = Branco
                )
            )
        },

        bottomBar = {
            NavigationBar(
                containerColor = AzulGov
            ) {

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("home") },
                    icon = {},
                    label = {
                        Text("Home", color = Branco)
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("mapa") },
                    icon = {},
                    label = {
                        Text("UBS", color = Branco)
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("historico") },
                    icon = {},
                    label = {
                        Text("Histórico", color = Branco)
                    }
                )
            }
        }

    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(CinzaClaro)
        ) {
            content()
        }
    }

}
