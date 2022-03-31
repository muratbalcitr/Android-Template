package com.m2lifeApps.data.remote.response

import com.squareup.moshi.Json

data class ApiError(
    @Json(name = "status_message")
    val statusMessage: String,
    @Json(name = "success")
    val isSuccess: Boolean,
    @Json(name = "status_code")
    val statusCode: Int
)
