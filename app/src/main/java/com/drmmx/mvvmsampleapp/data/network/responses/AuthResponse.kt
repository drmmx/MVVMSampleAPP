package com.drmmx.mvvmsampleapp.data.network.responses

import com.drmmx.mvvmsampleapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)