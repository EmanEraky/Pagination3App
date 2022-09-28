package com.eman.paginationapp.di.module

import com.eman.paginationapp.data.repository.RepoStorage
import com.eman.paginationapp.domain.useCase.MainRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val repoModule = module {
    single { MainRepository(get()) }
    single { RepoStorage(androidContext()) }
//    single<ApiHelper> {
//        return@single ApiHelperImpl(get())
//    }
}