package com.drmmx.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.drmmx.mvvmsampleapp.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}
