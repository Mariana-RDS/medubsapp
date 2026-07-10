package com.example.medicamentosubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.ui.theme.AzulGov
import com.example.medicamentosubs.ui.theme.Branco
import com.example.medicamentosubs.ui.theme.CinzaClaro
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

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

                actions = {

                    TextButton(
                        onClick = {
                            Firebase.auth.signOut()

                            navController.navigate("login") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    ) {
                        Text("Sair", color = Branco)
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulGov,
                    titleContentColor = Branco
                )
            )
        },

        bottomBar = {
            NavigationBar(
                containerColor = AzulGov,
                tonalElevation = 0.dp,
                modifier = Modifier.height(64.dp)
            ) {

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("home") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.casa),
                            contentDescription = "Home",
                            tint = Branco,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    alwaysShowLabel = false
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("mapa") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ubs),
                            contentDescription = "UBS",
                            tint = Branco,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    alwaysShowLabel = false
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("historico") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.lista),
                            contentDescription = "Histórico",
                            tint = Branco,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    alwaysShowLabel = false
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