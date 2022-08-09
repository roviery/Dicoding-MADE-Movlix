package com.roviery.core.data.source.local.room

import androidx.room.*
import com.roviery.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovlixDao {

    // Movie

    @Query("SELECT * FROM Movie")
    fun getAllPopularMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM FavoriteMovie")
    fun getFavoriteMovie(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM SearchMovie WHERE movieTitle LIKE '%' || :query || '%'")
    fun searchMovie(query: String?): Flow<List<SearchMovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM FavoriteMovie WHERE movieId = :movieId)")
    fun isExistInFavoriteMovie(movieId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchMovie(searchMovie: List<SearchMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: FavoriteMovieEntity)

    @Delete
    fun deleteFavoriteMovie(movie: FavoriteMovieEntity)

    // Tv Show

    @Query("SELECT * FROM TvShow")
    fun getAllPopularTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM FavoriteTvShow")
    fun getFavoriteTvShow(): Flow<List<FavoriteTvShowEntity>>

    @Query("SELECT * FROM SearchTvShow WHERE tvShowTitle LIKE '%' || :query || '%'")
    fun searchTvShow(query: String?): Flow<List<SearchTvShowEntity>>

    @Query("SELECT EXISTS(SELECT * FROM FavoriteTvShow WHERE tvShowId = :tvShowId)")
    fun isExistInFavoriteTvShow(tvShowId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchTvShow(searchTvShow: List<SearchTvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTvShow(tvShow: FavoriteTvShowEntity)

    @Delete
    fun deleteFavoriteTvShow(tvShow: FavoriteTvShowEntity)

}