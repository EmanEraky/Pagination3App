package com.eman.paginationapp.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.eman.paginationapp.data.api.StorageData
import com.eman.paginationapp.domain.models.Phonebook
import com.eman.paginationapp.utils.PreferencesKeys.DataStore_NAME
import com.eman.paginationapp.utils.PreferencesKeys.NAME
import com.eman.paginationapp.utils.PreferencesKeys.PHONE_NUMBER
import com.eman.paginationapp.utils.PreferencesKeys.ADDRESS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)

class RepoStorage(private val context: Context) : StorageData {
    override suspend fun savePhoneBook(phonebook: Phonebook) {
        context.datastore.edit { phonebooks ->
            phonebooks[NAME] = phonebook.name
            phonebooks[ADDRESS] = phonebook.address
            phonebooks[PHONE_NUMBER] = phonebook.phone
        }
    }

    override suspend fun getPhoneBook(): Flow<Phonebook> {
        return context.datastore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("TAG", "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it ->
            Phonebook(
                it[NAME]!!,
                it[ADDRESS]!!,
                it[PHONE_NUMBER]!!,
            )
        }
    }
}