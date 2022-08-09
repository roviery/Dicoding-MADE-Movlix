package com.roviery.core.domain.usecase

import com.roviery.core.data.source.Resource
import com.roviery.core.domain.model.Movie
import com.roviery.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {

    fun getPopularTvShow(): Flow<Resource<List<TvShow>>>

    fun searchTvShow(query: String): Flow<Resource<List<TvShow>>>

    fun getFavoriteTvShow(): Flow<List<TvShow>>

    fun insertFavoriteTvShow(tvShow: TvShow)

    fun isExistInFavoriteTvShow(tvShowId: Int): Boolean

    fun deleteFavoriteTvShow(tvShow: TvShow)

}