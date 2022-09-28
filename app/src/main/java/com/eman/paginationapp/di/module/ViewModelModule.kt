package com.eman.paginationapp.di.module

import com.eman.paginationapp.presentation.ui.Main.MainViewModel
import com.eman.paginationapp.presentation.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(),get()) }
    viewModel { UserViewModel(get()) }

}