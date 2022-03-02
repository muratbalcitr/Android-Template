package com.m2lifeApps.movieDb.features.main.domain

import com.m2lifeApps.data.Result
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.data.repository.ApiRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    val apiRepository: ApiRepository
) {
    suspend fun fetchPopular(): Result<PopularMoviesResponse> {
        return apiRepository.fetchPopular()
    }
}
