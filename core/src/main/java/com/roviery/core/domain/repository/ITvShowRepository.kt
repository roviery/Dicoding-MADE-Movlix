package com.roviery.core.domain.repository

import com.roviery.core.data.source.Resource
import com.roviery.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {

    fun getPopularTvShow(): Flow<Resource<List<TvShow>>>

    fun searchTvShow(query: String): Flow<Resource<List<TvShow>>>

    fun getFavoritePopularTvShow(): Flow<List<TvShow>>

    fun setFavoritePopularTvShow(tvShow: TvShow, state: Boolean)

}