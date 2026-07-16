package com.example.medicamentosubs.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.UserProfileChangeRequest

object FirebaseH {

    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    fun register(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful) {

                    val firebaseUser = auth.currentUser

                    if (firebaseUser != null) {

                        val uid = firebaseUser.uid

                        val profileUpdates =
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build()

                        firebaseUser.updateProfile(profileUpdates)

                        val user = hashMapOf(
                            "name" to name,
                            "email" to email
                        )


                        db.collection("users")
                            .document(uid)
                            .set(user)
                            .addOnSuccessListener {
                                onResult(true, null)
                            }
                            .addOnFailureListener {
                                onResult(false, it.message)
                            }

                    } else {
                        onResult(false, "Usuário não cadastrado")
                    }

                } else {
                    onResult(false, it.exception?.message)
                }
            }
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, it.exception?.message)
                }
            }
    }
}