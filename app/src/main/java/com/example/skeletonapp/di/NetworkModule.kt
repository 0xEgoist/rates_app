package com.example.skeletonapp.di

import com.example.data.repository.remote.rates.IRatesApi
import com.example.skeletonapp.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    private const val CONNECT_TIMEOUT_IN_SECONDS = 10
    private const val READ_TIMEOUT_IN_SECONDS = 60
    private const val WRITE_TIMEOUT_IN_SECONDS = 60

    /**
     * provides an Interceptor object to enable logging http request/response,
     * based on the defined log level
     *
     */
    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i(message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    /**
     * provides a custom OkHTPP object to be used a retrofit client
     * it could be used as a standalone http client
     */
    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)


        //Enable logging in debug mode only
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(loggingInterceptor)
            clientBuilder.addNetworkInterceptor(StethoInterceptor())
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hiring.revolut.codes/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRatesApi(retrofit: Retrofit): IRatesApi {
        return retrofit.create(IRatesApi::class.java)
    }
}