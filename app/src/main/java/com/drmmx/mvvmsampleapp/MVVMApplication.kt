package com.drmmx.mvvmsampleapp

import android.app.Application
import com.drmmx.mvvmsampleapp.data.db.AppDatabase
import com.drmmx.mvvmsampleapp.data.network.MyApi
import com.drmmx.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.drmmx.mvvmsampleapp.data.preferences.PreferenceProvider
import com.drmmx.mvvmsampleapp.data.repositories.QuotesRepository
import com.drmmx.mvvmsampleapp.data.repositories.UserRepository
import com.drmmx.mvvmsampleapp.ui.auth.AuthViewModelFactory
import com.drmmx.mvvmsampleapp.ui.home.profile.ProfileViewModelFactory
import com.drmmx.mvvmsampleapp.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }
}