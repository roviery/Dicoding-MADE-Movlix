package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.FavoriteTvShowEntity
import com.roviery.core.domain.model.TvShow

object DataMapperFavoriteTvShow {

    fun mapEntitiesToDomain(input: List<FavoriteTvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                tvShowId = it.tvShowId,
                tvShowTitle = it.tvShowTitle,
                tvShowPoster = it.tvShowPoster,
                tvShowBackdrop = it.tvShowBackdrop,
                tvShowReleaseDate = it.tvShowReleaseDate,
                tvShowOverview = it.tvShowOverview,
                tvShowVote = it.tvShowVote
            )
        }

    fun mapDomainToEntity(input: TvShow) =
        FavoriteTvShowEntity(
            tvShowId = input.tvShowId,
            tvShowTitle = input.tvShowTitle,
            tvShowPoster = input.tvShowPoster,
            tvShowBackdrop = input.tvShowBackdrop,
            tvShowReleaseDate = input.tvShowReleaseDate,
            tvShowOverview = input.tvShowOverview,
            tvShowVote = input.tvShowVote
        )

}