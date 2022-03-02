package com.m2lifeApps.data.repository

import androidx.annotation.VisibleForTesting
import com.m2lifeApps.data.Result
import com.m2lifeApps.data.remote.ProjectService
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import javax.inject.Inject

class ApiRepository @Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val service: ApiDataSource
) {
    suspend fun fetchPopular(): Result<PopularMoviesResponse> {
        return try {
            Result.Success(service.fetchPopular())
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
