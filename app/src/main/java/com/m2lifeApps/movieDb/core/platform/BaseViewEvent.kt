package com.m2lifeApps.movieDb.core.platform


sealed class BaseViewEvent {


    object ShowCommonNetworkError : BaseViewEvent()

    object ShowConnectivityError : BaseViewEvent()

    data class ShowCustomError(val message: String) : BaseViewEvent()
}