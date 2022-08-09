package com.roviery.core.data.source.local

import com.roviery.core.data.source.local.entity.*
import com.roviery.core.data.source.local.room.MovlixDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movlixDao: MovlixDao) {

    // Movie

    fun getAllPopularMovie(): Flow<List<MovieEntity>> = movlixDao.getAllPopularMovie()

    fun getFavoriteMovie(): Flow<List<FavoriteMovieEntity>> = movlixDao.getFavoriteMovie()

    fun searchMovie(query: String): Flow<List<SearchMovieEntity>> = movlixDao.searchMovie(query)

    fun insertMovie(listMovie: List<MovieEntity>) = movlixDao.insertMovie(listMovie)

    fun insertSearchMovie(listSearchMovie: List<SearchMovieEntity>) =
        movlixDao.insertSearchMovie(listSearchMovie)

    fun insertFavoriteMovie(movie: FavoriteMovieEntity) = movlixDao.insertFavoriteMovie(movie)

    fun isExistInFavoriteMovie(movieId: Int) = movlixDao.isExistInFavoriteMovie(movieId)

    fun deleteFavoriteMovie(movie: FavoriteMovieEntity) = movlixDao.deleteFavoriteMovie(movie)

    // Tv Show

    fun getAllPopularTvShow(): Flow<List<TvShowEntity>> = movlixDao.getAllPopularTvShow()

    fun getFavoriteTvShow(): Flow<List<FavoriteTvShowEntity>> = movlixDao.getFavoriteTvShow()

    fun searchTvShow(query: String): Flow<List<SearchTvShowEntity>> = movlixDao.searchTvShow(query)

    fun insertTvShow(listTvShow: List<TvShowEntity>) = movlixDao.insertTvShow(listTvShow)

    fun insertSearchTvShow(listSearchTvShow: List<SearchTvShowEntity>) =
        movlixDao.insertSearchTvShow(listSearchTvShow)

    fun insertFavoriteTvShow(tvShow: FavoriteTvShowEntity) = movlixDao.insertFavoriteTvShow(tvShow)

    fun isExistInFavoriteTvShow(tvShowId: Int) = movlixDao.isExistInFavoriteTvShow(tvShowId)

    fun deleteFavoriteTvShow(tvShow: FavoriteTvShowEntity) = movlixDao.deleteFavoriteTvShow(tvShow)
}