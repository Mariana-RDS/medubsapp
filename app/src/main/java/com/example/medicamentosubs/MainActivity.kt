package com.example.medicamentosubs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.medicamentosubs.navigation.AppNavigation
import com.example.medicamentosubs.ui.theme.MedicamentosUBSTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MedicamentosUBSTheme {
                AppNavigation()
            }
        }
    }
}