package com.roviery.favorite.di

import com.roviery.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get(), get()) }
}