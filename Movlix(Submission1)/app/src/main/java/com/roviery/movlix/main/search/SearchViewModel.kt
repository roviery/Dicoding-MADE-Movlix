package com.roviery.movlix.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.roviery.core.data.source.Resource
import com.roviery.core.domain.model.Movie
import com.roviery.core.domain.model.TvShow
import com.roviery.core.domain.usecase.MovieUseCase
import com.roviery.core.domain.usecase.TvShowUseCase

class SearchViewModel(private val movieUseCase: MovieUseCase, private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    fun searchMovie(query: String): LiveData<Resource<List<Movie>>> {
        return movieUseCase.searchMovie(query).asLiveData()
    }

    fun searchTvShow(query: String): LiveData<Resource<List<TvShow>>>{
        return tvShowUseCase.searchTvShow(query).asLiveData()
    }
}