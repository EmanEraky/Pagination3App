package com.eman.paginationapp.domain.useCase

import com.eman.paginationapp.data.api.ApiHelper
import com.eman.paginationapp.data.api.ApiService

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getPassenger() = apiHelper.getPassenger()

}