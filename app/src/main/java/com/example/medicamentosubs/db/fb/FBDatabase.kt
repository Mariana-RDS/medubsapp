package com.example.medicamentosubs.db.fb

import com.example.medicamentosubs.model.FBUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FBDatabase {

    interface Listener {
        fun onUserLoaded(user: FBUser)
        fun onUserSignOut()
    }

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var listener: Listener? = null

    init {
        auth.addAuthStateListener { auth ->

            if (auth.currentUser == null) {
                listener?.onUserSignOut()
                return@addAuthStateListener
            }

            val uid = auth.currentUser!!.uid

            db.collection("users").document(uid)
                .get()
                .addOnSuccessListener {
                    it.toObject(FBUser::class.java)?.let { user ->
                        listener?.onUserLoaded(user)
                    }
                }
        }
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    fun register(user: FBUser) {
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).set(user)
    }
}