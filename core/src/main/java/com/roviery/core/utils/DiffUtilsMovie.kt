package com.roviery.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.roviery.core.domain.model.Movie

class DiffUtilsMovie(private val oldList: List<Movie>, private val newList: List<Movie>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].movieId == newList[newItemPosition].movieId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (movieId1,
            movieTitle1,
            moviePoster1,
            movieBackdrop1,
            movieReleaseDate1,
            movieOverview1,
            movieVote1) = oldList[oldItemPosition]

        val (movieId2,
            movieTitle2,
            moviePoster2,
            movieBackdrop2,
            movieReleaseDate2,
            movieOverview2,
            movieVote2) = newList[newItemPosition]

        return movieId1 == movieId2
                && movieTitle1 == movieTitle2
                && moviePoster1 == moviePoster2
                && movieBackdrop1 == movieBackdrop2
                && movieReleaseDate1 == movieReleaseDate2
                && movieOverview1 == movieOverview2
                && movieVote1 == movieVote2
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}