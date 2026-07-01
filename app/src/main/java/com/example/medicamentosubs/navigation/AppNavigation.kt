package com.example.medicamentosubs.navigation

import androidx.compose.runtime.Composable
import com.example.medicamentosubs.ui.screens.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(navController)
        }

        composable("resultado/{medicamento}") { backStackEntry ->
            val medicamento = backStackEntry.arguments?.getString("medicamento") ?: ""

            ResultadoScreen(
                navController = navController,
                medicamento = medicamento
            )
        }

        composable("detalhe") {
            DetalheUBSScreen(navController)
        }

        composable("historico") {
            HistoricoScreen(navController)
        }

        composable("mapa") {
            MapaScreen(navController)
        }

    }
}