package com.eman.paginationapp.data.api

import com.eman.paginationapp.domain.models.ResponseUser
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("passenger")
    suspend fun getPassenger(
        @Query("page") page: Int,
        @Query("size") size: Int): ResponseUser

}