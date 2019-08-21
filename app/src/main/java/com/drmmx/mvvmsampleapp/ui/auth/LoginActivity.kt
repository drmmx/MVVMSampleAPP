package com.drmmx.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.drmmx.mvvmsampleapp.R
import com.drmmx.mvvmsampleapp.databinding.ActivityLoginBinding
import com.drmmx.mvvmsampleapp.util.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.vievModel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            progressBar.gone()
            toast(it)
        })
    }

    override fun onFailure(message: String) {
        progressBar.gone()
        toast(message)
    }
}
