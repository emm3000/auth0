package com.emm.auth0app.data.ds.sharedpreferences

import android.content.SharedPreferences

class SharedPreferencesManagerImpl(
    private val preferencesEdit: SharedPreferences
) : SharedPreferencesManager {

    override fun insertString(id: String) {
        preferencesEdit.edit().putString("USER_ID", id).apply()
    }

    override fun getString(): String {
        return preferencesEdit.getString("USER_ID", "") ?: ""
    }
}