package com.m2lifeApps.movieDb.features.main.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.m2lifeApps.data.Result
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.movieDb.core.extensions.Event
import com.m2lifeApps.movieDb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            val response = useCase.fetchPopular()
            when (response) {
                is Result.Success -> _popular.postValue(response.data)
                is Result.Error -> handleException(response.exception)
            }.also { setLoading(false) }
        }
    }

    fun navigateToDetail(pop: PopularMoviesResponse.Result) {
        _event.postValue(Event(MainViewEvent.NavigateToDetails(pop)))
    }
}
