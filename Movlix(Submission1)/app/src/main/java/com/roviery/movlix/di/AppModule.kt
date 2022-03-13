package com.roviery.movlix.di

import com.roviery.core.domain.usecase.MovieInteractor
import com.roviery.core.domain.usecase.MovieUseCase
import com.roviery.core.domain.usecase.TvShowInteractor
import com.roviery.core.domain.usecase.TvShowUseCase
import com.roviery.movlix.detail.DetailViewModel
import com.roviery.movlix.main.home.HomeViewModel
import com.roviery.movlix.main.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
}