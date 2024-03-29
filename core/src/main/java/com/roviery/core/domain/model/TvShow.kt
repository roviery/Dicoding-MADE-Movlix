package com.roviery.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val tvShowId: Int,
    val tvShowTitle: String? = null,
    val tvShowPoster: String? = null,
    val tvShowBackdrop: String? = null,
    val tvShowReleaseDate: String? = null,
    val tvShowOverview: String? = null,
    val tvShowVote: Float? = null
) : Parcelable