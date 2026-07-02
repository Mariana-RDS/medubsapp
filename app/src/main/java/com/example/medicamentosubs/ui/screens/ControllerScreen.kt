package com.example.medicamentosubs.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {

        val user = Firebase.auth.currentUser

        navController.navigate(
            if (user != null) "home" else "login"
        ) {
            popUpTo("splash") { inclusive = true }
        }
    }
}