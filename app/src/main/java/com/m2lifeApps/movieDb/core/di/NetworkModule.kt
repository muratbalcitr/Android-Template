package com.m2lifeApps.movieDb.core.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.m2lifeApps.data.remote.APIKEY
import com.m2lifeApps.data.remote.ProjectService
import com.m2lifeApps.movieDb.BuildConfig
import com.m2lifeApps.movieDb.core.di.qualifers.DefaultOkHttpClientBuilder
import com.m2lifeApps.movieDb.core.di.qualifers.ProjectOkHttpClient
import com.m2lifeApps.movieDb.core.di.qualifers.ProjectRetrofit
import com.m2lifeApps.movieDb.core.network.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideProjectService(
        @ProjectRetrofit projectRetrofit: Retrofit
    ): ProjectService = projectRetrofit.create(ProjectService::class.java)

    @ProjectRetrofit
    @Provides
    fun provideProjectRetrofit(
        @ProjectOkHttpClient projectOkHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().apply {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        baseUrl(BuildConfig.SERVICE_URL)

        addConverterFactory(MoshiConverterFactory.create(moshi))
        addCallAdapterFactory(CoroutineCallAdapterFactory())
        client(projectOkHttpClient)
    }.build()

    @ProjectOkHttpClient
    @Provides
    fun provideProjectOkHttpClient(
        @DefaultOkHttpClientBuilder okHttpClientBuilder: OkHttpClient.Builder,
        @ApplicationContext context: Context
    ) = okHttpClientBuilder.apply {
    }.build()

    @Provides
    @DefaultOkHttpClientBuilder
    fun provideDefaultOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
            val newUrl = request.url.newBuilder().addQueryParameter("api_key", APIKEY).build()
            it.proceed(request.newBuilder().url(newUrl).build())
        }
        .addInterceptor(provideLoggingInterceptor())
        .callTimeout(DEFAULT_CALL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)

    @Provides
    @Singleton
    fun provideNetworkController(
        @ApplicationContext context: Context,
    ) = NetworkController(context)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}
