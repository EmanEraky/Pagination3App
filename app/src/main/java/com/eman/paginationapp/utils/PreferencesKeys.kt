package com.eman.paginationapp.utils

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    const val DataStore_NAME = "PHONEBOOK"
    val NAME = stringPreferencesKey("NAME")
    val PHONE_NUMBER = stringPreferencesKey("PHONE")
    val ADDRESS = stringPreferencesKey("ADDRESS")
}