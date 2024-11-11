package br.com.connectattoo.util

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class PreferencesHelper(
    private val settings: Settings
) {
    private val tokenKey = "auth_token"

    fun saveToken(token: String) {
        settings.putString(tokenKey, token)
    }

    fun getToken(): String? {
        return settings.getString(tokenKey, "")
    }

    fun clearToken() {
        settings.remove(tokenKey)
    }
}