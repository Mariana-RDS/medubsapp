package com.example.medicamentosubs.data

import androidx.compose.runtime.mutableStateListOf
import com.example.medicamentosubs.model.Historico
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object Repositorio {

    var historico = mutableStateListOf<Historico>()

    fun adicionarHistorico(item: Historico) {

        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: return

        Firebase.firestore
            .collection("historico")
            .add(item)
            .addOnSuccessListener {
                historico.add(0, item)
            }
    }
}