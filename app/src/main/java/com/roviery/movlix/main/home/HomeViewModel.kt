package com.roviery.movlix.main.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.domain.usecase.MovieUseCase
import com.roviery.core.domain.usecase.TvShowUseCase

class HomeViewModel(movieUseCase: MovieUseCase, tvShowUseCase: TvShowUseCase) : ViewModel() {
    val popularMovie = movieUseCase.getPopularMovie().asLiveData()
    val popularTvShow = tvShowUseCase.getPopularTvShow().asLiveData()

}