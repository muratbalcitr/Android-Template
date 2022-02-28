package com.m2lifeApps.movieDb.core.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.io.IOException

class NetworkController(
    val context: Context
) {

    @SuppressLint("MissingPermission")
    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}

class NetworkUnavailableException : IOException("Network connectivity could not established.")
class ServiceUnavailableException : IOException("Service unavailable.")
class NetworkConnectTimeoutException: IOException("Network connection timeout.")
