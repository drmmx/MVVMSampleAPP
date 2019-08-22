package com.drmmx.mvvmsampleapp.ui.auth

import com.drmmx.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}