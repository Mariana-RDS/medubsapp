package com.example.medicamentosubs.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.medicamentosubs.MainLayout

@Composable
fun MapaScreen(navController: NavController) {

    MainLayout(title = "UBS próximas",
        navController = navController) {
        Text("Aqui vai o mapa com localização das UBS")
    }

}
