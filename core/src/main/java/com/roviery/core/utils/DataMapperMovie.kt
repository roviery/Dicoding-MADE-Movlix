package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.MovieEntity
import com.roviery.core.data.source.remote.response.MovieResponse
import com.roviery.core.domain.model.Movie

object DataMapperMovie {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity>{
        val listMovie = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                movieTitle = it.movieTitle,
                moviePoster = it.moviePoster,
                movieBackdrop = it.movieBackdrop,
                movieReleaseDate = it.movieReleaseDate,
                movieOverview = it.movieOverview,
                movieVote = it.movieVote
            )
            listMovie.add(movie)
        }
        return listMovie
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                movieTitle = it.movieTitle,
                moviePoster = it.moviePoster,
                movieBackdrop = it.movieBackdrop,
                movieReleaseDate = it.movieReleaseDate,
                movieOverview = it.movieOverview,
                movieVote = it.movieVote
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            movieId = input.movieId,
            movieTitle = input.movieTitle,
            moviePoster = input.moviePoster,
            movieBackdrop = input.movieBackdrop,
            movieReleaseDate = input.movieReleaseDate,
            movieOverview = input.movieOverview,
            movieVote = input.movieVote
        )

}