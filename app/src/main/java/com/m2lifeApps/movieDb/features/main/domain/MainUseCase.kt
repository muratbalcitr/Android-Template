package com.m2lifeApps.movieDb.features.main.domain

import com.m2lifeApps.data.Resource
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.data.repository.ApiRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MainUseCase @Inject constructor(
    val apiRepository: ApiRepository
) {
    fun fetchPopular(): Observable<Resource<PopularMoviesResponse>>{
        return apiRepository.fetchPopular()
    }
}