package com.pv.demoapp.di

import com.pv.demoapp.ui.city.CityViewModel
import com.pv.demoapp.ui.main.MainViewModel
import com.pv.demoapp.ui.register.RegisterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModule: Module = module {

    viewModel { MainViewModel(get(),get()) }
    viewModel { CityViewModel(get(), get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }

}