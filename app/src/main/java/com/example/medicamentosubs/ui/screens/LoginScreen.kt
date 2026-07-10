package com.example.medicamentosubs.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicamentosubs.data.FirebaseH
import com.example.medicamentosubs.ui.theme.AmareloGov
import com.example.medicamentosubs.ui.theme.AzulClaro
import com.example.medicamentosubs.ui.theme.AzulGov
import com.example.medicamentosubs.ui.theme.Branco
import com.example.medicamentosubs.ui.theme.CinzaClaro
import com.example.medicamentosubs.ui.theme.Preto

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulGov),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = CardDefaults.cardColors(containerColor = Branco)
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Entrar no sistema",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Acesse sua conta para continuar",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Preto.copy(alpha = 0.9f)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail", color = Preto) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Preto,
                        unfocusedTextColor = Preto,
                        focusedBorderColor = AzulGov,
                        unfocusedBorderColor = Preto,
                        cursorColor = Preto,
                        focusedLabelColor = Preto,
                        unfocusedLabelColor = Preto
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha", color = Preto) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Preto,
                        unfocusedTextColor = Preto,
                        focusedBorderColor = AzulGov,
                        unfocusedBorderColor = Preto,
                        cursorColor = Preto,
                        focusedLabelColor = Preto,
                        unfocusedLabelColor = Preto
                    )
                )

                Button(
                    onClick = {
                        FirebaseH.login(email, password) { success, error ->
                            if (success) {

                                Toast.makeText(context, "Login OK", Toast.LENGTH_SHORT).show()

                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                    launchSingleTop = true
                                }

                            } else {
                                Toast.makeText(context, error ?: "Erro", Toast.LENGTH_LONG).show()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AmareloGov)
                ) {
                    Text("Entrar", color = Branco)
                }

                TextButton(
                    onClick = { navController.navigate("register") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Criar conta", color = AzulClaro)
                }
            }
        }
    }
}