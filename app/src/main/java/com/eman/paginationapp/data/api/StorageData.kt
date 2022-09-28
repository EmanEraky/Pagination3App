package com.eman.paginationapp.data.api

import com.eman.paginationapp.domain.models.Phonebook
import kotlinx.coroutines.flow.Flow

interface StorageData {

    suspend fun savePhoneBook(phonebook: Phonebook)

    suspend fun getPhoneBook(): Flow<Phonebook>
}