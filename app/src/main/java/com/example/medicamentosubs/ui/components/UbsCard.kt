package com.example.medicamentosubs.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.medicamentosubs.model.UBS
import com.example.medicamentosubs.ui.theme.AmareloGov
import com.example.medicamentosubs.ui.theme.Preto

@Composable
fun UbsCard(
    ubs: UBS,
    onClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = ubs.nome,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = ubs.endereco,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AmareloGov,
                    contentColor = Preto
                )
            ) {
                Text("Pesquisar")
            }
        }
    }
}