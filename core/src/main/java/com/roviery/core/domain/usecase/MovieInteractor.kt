package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.Movie
import com.roviery.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getPopularMovie() = movieRepository.getPopularMovie()

    override fun searchMovie(query: String) = movieRepository.searchMovie(query)

    override fun getFavoritePopularMovie() = movieRepository.getFavoritePopularMovie()

    override fun setFavoritePopularMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoritePopularMovie(movie, state)

}