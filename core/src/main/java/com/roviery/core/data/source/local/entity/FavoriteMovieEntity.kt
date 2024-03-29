package com.roviery.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteMovie")
data class FavoriteMovieEntity(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "movieTitle")
    var movieTitle: String? = null,

    @ColumnInfo(name = "moviePoster")
    var moviePoster: String? = null,

    @ColumnInfo(name = "movieBackdrop")
    var movieBackdrop: String? = null,

    @ColumnInfo(name = "movieReleaseDate")
    var movieReleaseDate: String? = null,

    @ColumnInfo(name = "movieOverview")
    var movieOverview: String? = null,

    @ColumnInfo(name = "movieVote")
    var movieVote: Float? = null,

)
