package com.m2lifeApps.movieDb.core.network

import com.m2lifeApps.movieDb.core.platform.ProjectApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ApiRequestInterceptor @Inject constructor(
    private val networkController: NetworkController
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkController.isConnected()) {
            val exception = NetworkUnavailableException()
            ProjectApplication.networkStatusObservable.postValue(exception)
            throw exception
        }

        val request = addHeaderToRequest(
            request = chain.request()
        )

        return chain.proceed(request)

    }

    private fun addHeaderToRequest(request: Request): Request {
        val newRequest = request.newBuilder()

        /** If you want to add Token or Header value   */
        // newRequest.addHeader(HEADER_NAME, HEADER_VALUE)

        return newRequest.build()
    }

    companion object {
        const val HEADER_NAME = "header_name"
        const val HEADER_VALUE = "header_value"
    }
}

