package com.roviery.movlix.detail

import androidx.lifecycle.ViewModel
import com.roviery.core.domain.model.Movie
import com.roviery.core.domain.model.TvShow
import com.roviery.core.domain.usecase.MovieUseCase
import com.roviery.core.domain.usecase.TvShowUseCase

class DetailViewModel(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    fun setFavoritePopularMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoritePopularMovie(movie, newStatus)

    fun setFavoritePopularTvShow(tvShow: TvShow, newStatus: Boolean) =
        tvShowUseCase.setFavoritePopularTvShow(tvShow, newStatus)
}