package com.m2lifeApps.movieDb.features.main.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.m2lifeApps.data.remote.response.PopularMoviesResponse
import com.m2lifeApps.movieDb.core.platform.BaseListAdapter
import com.m2lifeApps.movieDb.core.platform.BaseViewHolder
import com.m2lifeApps.movieDb.databinding.ViewholderMostPopularBinding
import com.m2lifeApps.movieDb.features.main.domain.MainViewModel
class MovieListAdapter(
    val viewModel: MainViewModel,
    val list: List<PopularMoviesResponse.Result?>?
) : BaseListAdapter<PopularMoviesResponse.Result>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return PopularListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PopularListViewHolder -> {
                list?.get(position)?.let { holder.bind(viewModel, it) }
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}

class PopularListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    BaseViewHolder<ViewholderMostPopularBinding>(
        binding = ViewholderMostPopularBinding.inflate(inflater, parent, false)
    ) {
    fun bind(
        viewModels: MainViewModel,
        items: PopularMoviesResponse.Result
    ) {

        binding.apply {
            movie = items
            viewModel = viewModels

            executePendingBindings()
        }
    }
}
