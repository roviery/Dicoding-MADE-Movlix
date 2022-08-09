package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.FavoriteMovieEntity
import com.roviery.core.domain.model.Movie

object DataMapperFavoriteMovie {

    fun mapEntitiesToDomain(input: List<FavoriteMovieEntity>): List<Movie> =
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
        FavoriteMovieEntity(
            movieId = input.movieId,
            movieTitle = input.movieTitle,
            moviePoster = input.moviePoster,
            movieBackdrop = input.movieBackdrop,
            movieReleaseDate = input.movieReleaseDate,
            movieOverview = input.movieOverview,
            movieVote = input.movieVote
        )

}