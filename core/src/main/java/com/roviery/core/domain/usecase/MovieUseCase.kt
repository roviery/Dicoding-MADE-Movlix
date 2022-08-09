package com.roviery.core.domain.usecase

import com.roviery.core.data.source.Resource
import com.roviery.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovie(): Flow<Resource<List<Movie>>>

    fun searchMovie(query: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun insertFavoriteMovie(movie: Movie)

    fun isExistInFavoriteMovie(movieId: Int): Boolean

    fun deleteFavoriteMovie(movie:Movie)

}