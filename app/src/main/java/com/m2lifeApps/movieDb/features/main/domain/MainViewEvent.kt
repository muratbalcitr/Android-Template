package com.m2lifeApps.movieDb.features.main.domain

import com.m2lifeApps.data.remote.response.PopularMoviesResponse

sealed class MainViewEvent {
    data class NavigateToDetails(val item: PopularMoviesResponse.Result) : MainViewEvent()
}
