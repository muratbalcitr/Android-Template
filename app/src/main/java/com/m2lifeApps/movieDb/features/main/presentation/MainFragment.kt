package com.m2lifeApps.movieDb.features.main.presentation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.movieDb.R
import com.m2lifeApps.movieDb.core.common.PageName
import com.m2lifeApps.movieDb.core.extensions.observeEvent
import com.m2lifeApps.movieDb.core.platform.BaseFragment
import com.m2lifeApps.movieDb.databinding.FragmentMainBinding
import com.m2lifeApps.movieDb.features.main.domain.MainViewEvent
import com.m2lifeApps.movieDb.features.main.domain.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(
    layoutId = R.layout.fragment_main,
    viewModelClass = MainViewModel::class.java
) {

    override fun getDeepLinkName(): String {
        return PageName.MAIN
    }

    override fun onInitDataBinding() {
        observeEvent(viewModel.event, ::onViewEvent)
        binding.viewModel = viewModel
        binding.appCompatTextView.setOnClickListener {
            viewModel.fetchPopulars()
        }
        viewModel.fetchPopulars()
        viewModel.popular.observe(this) {
            initAdapter(it)
        }
    }

   private fun onViewEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.NavigateToDetails -> {
                val bundle = Bundle()
                bundle.putString("data", Gson().toJson(event.item))
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
            }
        }
    }

    private fun initAdapter(moviesResponse: PopularMoviesResponse?) {
        val adapters = MovieListAdapter(viewModel, moviesResponse?.results)
        binding.recyclerView.apply {
            adapter = adapters
            setHasFixedSize(true)
        }
    }
}
