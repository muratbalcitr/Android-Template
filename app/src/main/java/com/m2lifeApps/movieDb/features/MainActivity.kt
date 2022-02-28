package com.m2lifeApps.movieDb.features

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.m2lifeApps.movieDb.R
import com.m2lifeApps.movieDb.core.platform.BaseActivity
import com.m2lifeApps.movieDb.databinding.ActivityMainBinding
import com.m2lifeApps.movieDb.features.main.domain.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(
        layoutId = R.layout.activity_main,
        viewModelClass = MainViewModel::class.java
    ) {

    @NavigationRes
    private val navigationGraph: Int =
        R.navigation.navigation_main_graph

    private lateinit var navGraph: NavGraph
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onInitDataBinding() {
        setNavigation()
    }

    private fun setNavigation() {
        val host = NavHostFragment.create(navigationGraph)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayout,
                host,
                host.tag
            )
            .setPrimaryNavigationFragment(host)
            .runOnCommit {
                this.navController = host.navController
            }.commit()
    }

    companion object {
        fun newIntent(context: Context, targetUrl: String = ""): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra("targetUrl", targetUrl)
            }
        }
    }
}
