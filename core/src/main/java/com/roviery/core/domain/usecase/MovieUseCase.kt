package com.roviery.core.domain.usecase

import com.roviery.core.data.source.Resource
import com.roviery.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovie(): Flow<Resource<List<Movie>>>

    fun searchMovie(query: String): Flow<Resource<List<Movie>>>

    fun getFavoritePopularMovie(): Flow<List<Movie>>

    fun setFavoritePopularMovie(movie: Movie, state: Boolean)

}