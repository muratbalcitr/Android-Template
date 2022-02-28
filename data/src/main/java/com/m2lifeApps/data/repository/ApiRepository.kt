package com.m2lifeApps.data.repository

import com.m2lifeApps.data.Resource
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.data.util.toObservable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiDataSource: ApiDataSource
) {
    fun fetchPopular(): Observable<Resource<PopularMoviesResponse>> {
        return Observable.create { emitter ->
            apiDataSource.fetchPopular().toObservable(emitter)
        }
    }
}
