package com.m2lifeApps.data.remote

import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

const val APIKEY = "6e79b829b9ag1efa18f14df89b178c8bb132"
interface ProjectService {
    object Movie {
        const val mainPath = "/"
        const val popular = "movie/popular"
    }

    @GET(Movie.popular)
    suspend fun fetchPopular(): PopularMoviesResponse
}
