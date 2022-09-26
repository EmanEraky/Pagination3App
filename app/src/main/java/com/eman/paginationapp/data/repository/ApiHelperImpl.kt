package com.eman.paginationapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eman.paginationapp.data.api.ApiHelper
import com.eman.paginationapp.data.api.ApiService
import com.eman.paginationapp.data.repository.UserPagingSource.Companion.NETWORK_PAGE_SIZE
import com.eman.paginationapp.domain.models.User
import kotlinx.coroutines.flow.Flow


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {


    override suspend fun getPassenger(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService = apiService)
            }
            , initialKey = 1
        ).flow
    }



}