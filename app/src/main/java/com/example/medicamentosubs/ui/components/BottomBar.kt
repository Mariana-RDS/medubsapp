package com.example.medicamentosubs.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("mapa") },
            icon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "UBS"
                )
            },
            label = { Text("UBS") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("historico") },
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Histórico"
                )
            },
            label = { Text("Histórico") }
        )
    }
}