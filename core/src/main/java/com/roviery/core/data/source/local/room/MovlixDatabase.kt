package com.roviery.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roviery.core.data.source.local.entity.*

@Database(entities = [MovieEntity::class, SearchMovieEntity::class, FavoriteMovieEntity::class,
    TvShowEntity::class, SearchTvShowEntity::class, FavoriteTvShowEntity::class], version = 1, exportSchema = false)
abstract class MovlixDatabase : RoomDatabase() {

    abstract fun movlixDao(): MovlixDao

}