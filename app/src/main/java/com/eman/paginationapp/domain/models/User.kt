package com.eman.paginationapp.domain.models

import com.squareup.moshi.Json


data class User(
    @Json(name = "_id")
    val id: String = "0",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "country")
    val country: String = "",
    @Json(name = "logo")
    val logo: String = ""
)