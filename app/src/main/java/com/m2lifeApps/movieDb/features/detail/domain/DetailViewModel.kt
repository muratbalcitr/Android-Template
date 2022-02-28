package com.m2lifeApps.movieDb.features.detail.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.m2lifeApps.data.Status
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.movieDb.core.platform.BaseViewModel
import com.m2lifeApps.movieDb.features.main.domain.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val useCase: MainUseCase
) : BaseViewModel() {

    private val _popular = MutableLiveData<PopularMoviesResponse?>()
    val popular: LiveData<PopularMoviesResponse?> = _popular

}
