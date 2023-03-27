package com.emm.auth0app.data.ds.sharedpreferences

interface SharedPreferencesManager {

    fun insertString(id: String)
    fun getString(): String

}