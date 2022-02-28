package com.m2lifeApps.movieDb.features.detail.presentation

import com.google.gson.Gson
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.movieDb.R
import com.m2lifeApps.movieDb.core.common.PageName
import com.m2lifeApps.movieDb.core.platform.BaseFragment
import com.m2lifeApps.movieDb.databinding.FragmentMovieDetailBinding
import com.m2lifeApps.movieDb.features.detail.domain.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentMovieDetailBinding, DetailViewModel>(
    layoutId = R.layout.fragment_movie_detail,
    viewModelClass = DetailViewModel::class.java
) {

    override fun getDeepLinkName(): String {
        return PageName.DETAIL
    }

    override fun onInitDataBinding() {
        val argument =
            Gson().fromJson(arguments?.getString("data"), PopularMoviesResponse.Result::class.java)
        binding.movie = argument
    }
}
