package com.eman.paginationapp.data.api

import androidx.paging.PagingData
import com.eman.paginationapp.domain.models.User
import com.eman.paginationapp.utils.Resource
import kotlinx.coroutines.flow.Flow


interface ApiHelper {
    suspend fun getPassenger(): Flow<PagingData<User>>
}