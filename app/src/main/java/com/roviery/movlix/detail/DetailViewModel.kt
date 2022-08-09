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

    fun insertFavoriteMovie(movie: Movie) =
        movieUseCase.insertFavoriteMovie(movie)

    fun isExistInFavoriteMovie(movieId: Int) =
        movieUseCase.isExistInFavoriteMovie(movieId)

    fun deleteFavoriteMovie(movie: Movie) = movieUseCase.deleteFavoriteMovie(movie)

    fun insertFavoriteTvShow(tvShow: TvShow) =
        tvShowUseCase.insertFavoriteTvShow(tvShow)

    fun isExistInFavoriteTvShow(tvShowId: Int) =
        tvShowUseCase.isExistInFavoriteTvShow(tvShowId)

    fun deleteFavoriteTvShow(tvShow: TvShow) = tvShowUseCase.deleteFavoriteTvShow(tvShow)

}