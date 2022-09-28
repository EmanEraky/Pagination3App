package com.eman.paginationapp.di.module

import android.content.Context
import com.eman.paginationapp.BuildConfig
import com.eman.paginationapp.data.api.ApiHelper
import com.eman.paginationapp.data.repository.ApiHelperImpl
import com.eman.paginationapp.data.api.ApiService
import com.eman.paginationapp.data.repository.RepoStorage
import com.eman.paginationapp.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import org.koin.dsl.module
import kotlin.math.sin


val appModule = module{
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

//private fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()


private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)



