package com.example.eldarwallet.data.datasource.local.prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefs @Inject constructor(@ApplicationContext context: Context) {

    private val PREFS_NAME = "my_prefs"
    private val KEY_USER_NAME = "user_name"
    private val KEY_LAST_NAME = "last_name"
    private val KEY_DEBT = "debt"
    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var name: String
        get() = sharedPreferences.getString(KEY_USER_NAME, "").orEmpty()
        set(value) {
            sharedPreferences.edit().putString(KEY_USER_NAME, value).apply()
        }

    var lastName: String
        get() = sharedPreferences.getString(KEY_LAST_NAME, "").orEmpty()
        set(value) {
            sharedPreferences.edit().putString(KEY_LAST_NAME, value).apply()
        }

    var debt: String
        get() = sharedPreferences.getString(KEY_DEBT, "0") ?: "0"
        set(value) {
            sharedPreferences.edit().putString(KEY_DEBT, value).apply()
        }

}