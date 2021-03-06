package com.roviery.core.data.source.local.room

import androidx.room.*
import com.roviery.core.data.source.local.entity.MovieEntity
import com.roviery.core.data.source.local.entity.SearchMovieEntity
import com.roviery.core.data.source.local.entity.SearchTvShowEntity
import com.roviery.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovlixDao {

    // Movie

    @Query("SELECT * FROM Movie")
    fun getAllPopularMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM Movie WHERE isFavorite = 1")
    fun getFavoritePopularMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM SearchMovie WHERE movieTitle LIKE '%' || :query || '%'")
    fun searchMovie(query: String?): Flow<List<SearchMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchMovie(searchMovie: List<SearchMovieEntity>)

    @Update
    fun updateFavoritePopularMovie(movie: MovieEntity)

    // Tv Show

    @Query("SELECT * FROM TvShow")
    fun getAllPopularTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM TvShow WHERE isFavorite = 1")
    fun getFavoritePopularTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM SearchTvShow WHERE tvShowTitle LIKE '%' || :query || '%'")
    fun searchTvShow(query: String?): Flow<List<SearchTvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchTvShow(searchTvShow: List<SearchTvShowEntity>)

    @Update
    fun updateFavoritePopularTvShow(tvShow: TvShowEntity)

}