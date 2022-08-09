package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.TvShow
import com.roviery.core.domain.repository.ITvShowRepository

class TvShowInteractor(private val tvShowRepository: ITvShowRepository) : TvShowUseCase {

    override fun getPopularTvShow() = tvShowRepository.getPopularTvShow()

    override fun searchTvShow(query: String) = tvShowRepository.searchTvShow(query)

    override fun getFavoriteTvShow() = tvShowRepository.getFavoriteTvShow()

    override fun insertFavoriteTvShow(tvShow: TvShow) =
        tvShowRepository.insertFavoriteTvShow(tvShow)

    override fun isExistInFavoriteTvShow(tvShowId: Int): Boolean =
        tvShowRepository.isExistInFavoriteTvShow(tvShowId)

    override fun deleteFavoriteTvShow(tvShow: TvShow) =
        tvShowRepository.deleteFavoriteTvShow(tvShow)


}