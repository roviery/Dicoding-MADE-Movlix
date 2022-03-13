package com.roviery.core.data.source.local

import com.roviery.core.data.source.local.entity.MovieEntity
import com.roviery.core.data.source.local.entity.SearchMovieEntity
import com.roviery.core.data.source.local.entity.SearchTvShowEntity
import com.roviery.core.data.source.local.entity.TvShowEntity
import com.roviery.core.data.source.local.room.MovlixDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movlixDao: MovlixDao) {

    // Movie

    fun getAllPopularMovie(): Flow<List<MovieEntity>> = movlixDao.getAllPopularMovie()

    fun getFavoritePopularMovie(): Flow<List<MovieEntity>> = movlixDao.getFavoritePopularMovie()

    fun searchMovie(query: String): Flow<List<SearchMovieEntity>> = movlixDao.searchMovie(query)

    suspend fun insertMovie(listMovie: List<MovieEntity>) = movlixDao.insertMovie(listMovie)

    suspend fun insertSearchMovie(listSearchMovie: List<SearchMovieEntity>) = movlixDao.insertSearchMovie(listSearchMovie)

    fun setFavoritePopularMovie(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movlixDao.updateFavoritePopularMovie(movie)
    }


    // Tv Show

    fun getAllPopularTvShow(): Flow<List<TvShowEntity>> = movlixDao.getAllPopularTvShow()

    fun getFavoritePopularTvShow(): Flow<List<TvShowEntity>> = movlixDao.getFavoritePopularTvShow()

    fun searchTvShow(query: String): Flow<List<SearchTvShowEntity>> = movlixDao.searchTvShow(query)

    suspend fun insertTvShow(listTvShow: List<TvShowEntity>) = movlixDao.insertTvShow(listTvShow)

    suspend fun  insertSearchTvShow(listSearchTvShow: List<SearchTvShowEntity>) = movlixDao.insertSearchTvShow(listSearchTvShow)

    fun setFavoritePopularTvShow(tvShow: TvShowEntity, newState: Boolean){
        tvShow.isFavorite = newState
        movlixDao.updateFavoritePopularTvShow(tvShow)
    }

}