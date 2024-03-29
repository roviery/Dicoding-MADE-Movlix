package com.roviery.core.data.source.repository

import com.roviery.core.data.source.NetworkBoundResource
import com.roviery.core.data.source.Resource
import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.data.source.remote.RemoteDataSource
import com.roviery.core.data.source.remote.network.ApiResponse
import com.roviery.core.data.source.remote.response.TvShowResponse
import com.roviery.core.domain.model.Movie
import com.roviery.core.domain.model.TvShow
import com.roviery.core.domain.repository.ITvShowRepository
import com.roviery.core.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITvShowRepository {
    override fun getPopularTvShow(): Flow<Resource<List<TvShow>>> =
        object :
            NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllPopularTvShow().map {
                    DataMapperTvShow.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getPopularTvShow()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val listTvShow = DataMapperTvShow.mapResponsesToEntities(data)
                localDataSource.insertTvShow(listTvShow)
            }
        }.asFlow()

    override fun getFavoriteTvShow(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShow().map {
            DataMapperFavoriteTvShow.mapEntitiesToDomain(it)
        }
    }

    override fun searchTvShow(query: String): Flow<Resource<List<TvShow>>> =
        object :
            NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.searchTvShow(query).map {
                    DataMapperSearchTvShow.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.searchTvShow(query)

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val listSearchMovieEntity = DataMapperSearchTvShow.mapResponsesToEntities(data)
                localDataSource.insertSearchTvShow(listSearchMovieEntity)
            }
        }.asFlow()

    override fun insertFavoriteTvShow(tvShow: TvShow) {
        val favoriteTvShowEntity = DataMapperFavoriteTvShow.mapDomainToEntity(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.insertFavoriteTvShow(favoriteTvShowEntity)
        }
    }

    override fun isExistInFavoriteTvShow(tvShowId: Int): Boolean {
        return localDataSource.isExistInFavoriteTvShow(tvShowId)
    }

    override fun deleteFavoriteTvShow(tvShow: TvShow) {
        val favoriteTvShowEntity = DataMapperFavoriteTvShow.mapDomainToEntity(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.deleteFavoriteTvShow(favoriteTvShowEntity)
        }
    }
}