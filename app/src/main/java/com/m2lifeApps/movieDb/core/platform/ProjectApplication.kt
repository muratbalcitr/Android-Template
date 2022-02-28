package com.m2lifeApps.movieDb.core.platform

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.multidex.MultiDex
import com.m2lifeApps.data.repository.ApiRepository
import com.m2lifeApps.movieDb.core.common.PreferenceManager
import com.m2lifeApps.movieDb.core.network.NetworkUnavailableException
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class ProjectApplication : Application() {

    @Inject
    lateinit var apiRepository: ApiRepository

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        injectMultiDex()
        Timber.plant(Timber.DebugTree())
      }

    private fun injectMultiDex() {
        MultiDex.install(this)
    }


    companion object {
        lateinit var appContext: Context
        val preferenceManager: PreferenceManager by lazy {
            PreferenceManager(appContext)
        }


        /**
         * Used for checking internet connectivity. Main usage is on [ProjectApiRequestInterceptor]
         */

        val networkStatusObservable: MutableLiveData<NetworkUnavailableException> by lazy {
            MutableLiveData()
        }
    }
}
