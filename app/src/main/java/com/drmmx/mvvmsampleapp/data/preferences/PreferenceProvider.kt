package com.drmmx.mvvmsampleapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(
    context: Context
) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedAt: Long) {
        preference.edit().putLong(KEY_SAVED_AT, savedAt).apply()
    }

    fun getLastSavedAt(): Long?{
        return preference.getLong(KEY_SAVED_AT, 0L)
    }
}