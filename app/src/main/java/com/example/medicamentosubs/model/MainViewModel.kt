package com.example.medicamentosubs.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.medicamentosubs.db.fb.FBDatabase

class MainViewModel(private val db: FBDatabase) :
    ViewModel(), FBDatabase.Listener {

    private val _user = mutableStateOf<User?>(null)
    val user: User?
        get() = _user.value

    init {
        db.setListener(this)
    }

    override fun onUserLoaded(user: FBUser) {
        _user.value = user.toUser()
    }

    override fun onUserSignOut() {
        _user.value = null
    }
}