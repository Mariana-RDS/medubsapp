package com.example.medicamentosubs.navigation

import androidx.compose.runtime.Composable
import com.example.medicamentosubs.ui.screens.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicamentosubs.model.MainViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun AppNavigation(viewModel: MainViewModel) {

    val navController = rememberNavController()

    val isLogged = Firebase.auth.currentUser != null

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }

        composable("home") {
            HomeScreen(navController, viewModel)
        }

        composable("historico") {
            HistoricoScreen(navController)
        }

        composable("mapa") {
            MapaScreen(navController)
        }

        composable(
            route = "detalhe/{ubs}/{medicamento}"
        ) { backStackEntry ->

            val ubs = backStackEntry.arguments?.getString("ubs") ?: ""
            val medicamento = backStackEntry.arguments?.getString("medicamento") ?: ""

            DetalheUBSScreen(
                navController = navController,
                nomeUBS = ubs,
                medicamento = medicamento
            )
        }

        composable("resultado/{medicamento}") { backStackEntry ->
            val medicamento = backStackEntry.arguments?.getString("medicamento") ?: ""
            ResultadoScreen(navController, medicamento)
        }

        composable(
            route = "detalheMapa/{nome}/{endereco}/{bairro}/{latitude}/{longitude}"
        ) { backStackEntry ->

            val nome = backStackEntry.arguments?.getString("nome") ?: ""
            val endereco = backStackEntry.arguments?.getString("endereco") ?: ""
            val bairro = backStackEntry.arguments?.getString("bairro") ?: ""
            val latitude = backStackEntry.arguments?.getString("latitude") ?: ""
            val longitude = backStackEntry.arguments?.getString("longitude") ?: ""


            DetalheMapaUBSScreen(
                navController = navController,
                nome = nome,
                endereco = endereco,
                bairro = bairro,
                latitude = latitude,
                longitude = longitude
            )
        }
    }
}