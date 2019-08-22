package com.drmmx.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.drmmx.mvvmsampleapp.R
import com.drmmx.mvvmsampleapp.data.db.AppDatabase
import com.drmmx.mvvmsampleapp.data.db.entities.User
import com.drmmx.mvvmsampleapp.data.network.MyApi
import com.drmmx.mvvmsampleapp.data.repositories.UserRepository
import com.drmmx.mvvmsampleapp.databinding.ActivityLoginBinding
import com.drmmx.mvvmsampleapp.ui.home.HomeActivity
import com.drmmx.mvvmsampleapp.util.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = MyApi()
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.vievModel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(user: User) {
        progressBar.gone()
    }

    override fun onFailure(message: String) {
        progressBar.gone()
        rootLayout.snackbar(message)
    }
}
