package com.m2lifeApps.movieDb.features.main.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.m2lifeApps.data.Status
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.movieDb.core.extensions.Event
import com.m2lifeApps.movieDb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val useCase: MainUseCase
) : BaseViewModel() {

    private val _popular = MutableLiveData<PopularMoviesResponse?>()
    val popular: LiveData<PopularMoviesResponse?> = _popular

    private val _event = MutableLiveData<Event<MainViewEvent>>()
    val event: LiveData<Event<MainViewEvent>> = _event

    fun fetchPopulars() {
        val response = useCase.fetchPopular()
        response.subscribe {
            when (it.status) {
                Status.SUCCESS -> {
                    _popular.postValue(it.data)
                }
                Status.ERROR -> handleException(Exception(it.error))
            }
        }
    }

    fun navigateToDetail(pop: PopularMoviesResponse.Result) {
        _event.postValue(Event(MainViewEvent.NavigateToDetails(pop)))
    }
}
