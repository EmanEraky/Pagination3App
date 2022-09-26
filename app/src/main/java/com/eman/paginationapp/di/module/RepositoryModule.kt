package com.eman.paginationapp.di.module

import com.eman.paginationapp.domain.useCase.MainRepository
import org.koin.dsl.module


val repoModule = module {
    single {
        MainRepository(get())
    }
//    single<ApiHelper> {
//        return@single ApiHelperImpl(get())
//    }
}