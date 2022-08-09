package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Movie
import com.roviery.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getPopularMovie() = movieRepository.getPopularMovie()

    override fun searchMovie(query: String) = movieRepository.searchMovie(query)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun insertFavoriteMovie(movie: Movie) =
        movieRepository.insertFavoriteMovie(movie)

    override fun isExistInFavoriteMovie(movieId: Int): Boolean =
        movieRepository.isExistInFavoriteMovie(movieId)

    override fun deleteFavoriteMovie(movie: Movie) {
        movieRepository.deleteFavoriteMovie(movie)
    }

}