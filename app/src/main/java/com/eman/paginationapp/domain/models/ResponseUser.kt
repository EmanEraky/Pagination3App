package com.eman.paginationapp.domain.models

import com.squareup.moshi.Json

data class ResponseUser (
    @Json(name = "totalPassengers")
    val totalPassengers:Int,
    @Json(name = "totalPages")
    val totalPages :Int ,
    @Json(name = "data")
    val data :List<User>
)