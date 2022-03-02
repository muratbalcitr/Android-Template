package com.m2lifeApps.movieDb.features.detail.domain

import com.m2lifeApps.data.Result
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.data.repository.ApiRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    val apiRepository: ApiRepository
) {
}