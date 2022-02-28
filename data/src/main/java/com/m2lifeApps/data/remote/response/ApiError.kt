package com.m2lifeApps.data.remote.response

import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val isSuccess: Boolean,
    @SerializedName("status_code")
    val statusCode: Int,

)
