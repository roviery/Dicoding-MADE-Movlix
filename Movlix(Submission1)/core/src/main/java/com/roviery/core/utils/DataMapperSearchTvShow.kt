package com.roviery.core.utils

import com.roviery.core.data.source.local.entity.SearchTvShowEntity
import com.roviery.core.data.source.remote.response.TvShowResponse
import com.roviery.core.domain.model.TvShow

object DataMapperSearchTvShow {

    fun mapResponsesToEntities(input: List<TvShowResponse>): List<SearchTvShowEntity> {
        val listTvShow = ArrayList<SearchTvShowEntity>()
        input.map {
            val tvShow = SearchTvShowEntity(
                tvShowId = it.tvShowId,
                tvShowTitle = it.tvShowTitle,
                tvShowPoster = it.tvShowPoster,
                tvShowBackdrop = it.tvShowBackdrop,
                tvShowReleaseDate = it.tvShowReleaseDate,
                tvShowOverview = it.tvShowOverview,
                tvShowVote = it.tvShowVote,
                isFavorite = false
            )
            listTvShow.add(tvShow)
        }
        return listTvShow
    }

    fun mapEntitiesToDomain(input: List<SearchTvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                tvShowId = it.tvShowId,
                tvShowTitle = it.tvShowTitle,
                tvShowPoster = it.tvShowPoster,
                tvShowBackdrop = it.tvShowBackdrop,
                tvShowReleaseDate = it.tvShowReleaseDate,
                tvShowOverview = it.tvShowOverview,
                tvShowVote = it.tvShowVote,
                isFavorite = it.isFavorite
            )
        }

}