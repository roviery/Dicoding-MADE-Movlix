package com.roviery.core.domain.usecase

import com.roviery.core.domain.model.TvShow
import com.roviery.core.domain.repository.ITvShowRepository

class TvShowInteractor(private val tvShowRepository: ITvShowRepository) : TvShowUseCase {

    override fun getPopularTvShow() = tvShowRepository.getPopularTvShow()

    override fun searchTvShow(query: String) = tvShowRepository.searchTvShow(query)

    override fun getFavoritePopularTvShow() = tvShowRepository.getFavoritePopularTvShow()

    override fun setFavoritePopularTvShow(tvShow: TvShow, state: Boolean) =
        tvShowRepository.setFavoritePopularTvShow(tvShow, state)

}