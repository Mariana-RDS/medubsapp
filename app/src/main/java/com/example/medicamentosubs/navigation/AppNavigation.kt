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
    }
}