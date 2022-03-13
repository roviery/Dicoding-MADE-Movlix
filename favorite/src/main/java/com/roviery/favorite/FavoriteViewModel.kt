package com.roviery.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.domain.usecase.MovieUseCase
import com.roviery.core.domain.usecase.TvShowUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase, tvShowUseCase: TvShowUseCase) : ViewModel() {
    val favoritePopularMovie = movieUseCase.getFavoritePopularMovie().asLiveData()
    val favoritePopularTvShow = tvShowUseCase.getFavoritePopularTvShow().asLiveData()
}