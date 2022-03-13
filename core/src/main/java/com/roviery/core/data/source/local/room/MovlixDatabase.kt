package com.roviery.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roviery.core.data.source.local.entity.MovieEntity
import com.roviery.core.data.source.local.entity.SearchMovieEntity
import com.roviery.core.data.source.local.entity.SearchTvShowEntity
import com.roviery.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, SearchMovieEntity::class, TvShowEntity::class, SearchTvShowEntity::class], version = 1, exportSchema = false)
abstract class MovlixDatabase : RoomDatabase() {

    abstract fun movlixDao(): MovlixDao

}