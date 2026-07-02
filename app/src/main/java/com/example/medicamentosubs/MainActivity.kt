package com.example.medicamentosubs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicamentosubs.db.fb.FBDatabase
import com.example.medicamentosubs.model.MainViewModel
import com.example.medicamentosubs.model.MainViewModelFactory
import com.example.medicamentosubs.navigation.AppNavigation
import com.example.medicamentosubs.ui.theme.MedicamentosUBSTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MedicamentosUBSTheme {

                val fbDB = remember { FBDatabase() }

                val viewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(fbDB)
                )

                AppNavigation(viewModel)
            }
        }
    }
}