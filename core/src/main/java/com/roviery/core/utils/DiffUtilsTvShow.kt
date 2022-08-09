package com.roviery.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.roviery.core.domain.model.TvShow

class DiffUtilsTvShow(private val oldList: List<TvShow>, private val newList: List<TvShow>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].tvShowId == newList[newItemPosition].tvShowId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (tvShowId1,
            tvShowTitle1,
            tvShowPoster1,
            tvShowBackdrop1,
            tvShowReleaseDate1,
            tvShowOverview1,
            tvShowVote1) = oldList[oldItemPosition]

        val (tvShowId2,
            tvShowTitle2,
            tvShowPoster2,
            tvShowBackdrop2,
            tvShowReleaseDate2,
            tvShowOverview2,
            tvShowVote2) = newList[newItemPosition]

        return tvShowId1 == tvShowId2
                && tvShowTitle1 == tvShowTitle2
                && tvShowPoster1 == tvShowPoster2
                && tvShowBackdrop1 == tvShowBackdrop2
                && tvShowReleaseDate1 == tvShowReleaseDate2
                && tvShowOverview1 == tvShowOverview2
                && tvShowVote1 == tvShowVote2
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}