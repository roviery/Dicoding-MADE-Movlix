package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.TvShowEntity
import com.roviery.core.data.source.remote.response.TvShowResponse
import com.roviery.core.domain.model.TvShow

object DataMapperTvShow {

    fun mapResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val listTvShow = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                tvShowId = it.tvShowId,
                tvShowTitle = it.tvShowTitle,
                tvShowPoster = it.tvShowPoster,
                tvShowBackdrop = it.tvShowBackdrop,
                tvShowReleaseDate = it.tvShowReleaseDate,
                tvShowOverview = it.tvShowOverview,
                tvShowVote = it.tvShowVote
            )
            listTvShow.add(tvShow)
        }
        return listTvShow
    }

    fun mapEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
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
        TvShowEntity(
            tvShowId = input.tvShowId,
            tvShowTitle = input.tvShowTitle,
            tvShowPoster = input.tvShowPoster,
            tvShowBackdrop = input.tvShowBackdrop,
            tvShowReleaseDate = input.tvShowReleaseDate,
            tvShowOverview = input.tvShowOverview,
            tvShowVote = input.tvShowVote
        )

}