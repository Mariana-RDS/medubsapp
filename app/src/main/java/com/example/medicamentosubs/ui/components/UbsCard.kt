package com.example.medicamentosubs.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicamentosubs.model.UBS

@Composable
fun UbsCard(
    ubs: UBS,
    onClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp
            ),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = ubs.nome,
                style = MaterialTheme.typography.titleLarge
            )

            Text(ubs.endereco)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onClick) {
                Text("Abrir")
            }

        }

    }

}