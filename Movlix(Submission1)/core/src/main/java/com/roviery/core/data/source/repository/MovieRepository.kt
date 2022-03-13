package com.roviery.core.data.source.repository

import com.roviery.core.data.source.NetworkBoundResource
import com.roviery.core.data.source.Resource
import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.data.source.remote.RemoteDataSource
import com.roviery.core.data.source.remote.network.ApiResponse
import com.roviery.core.data.source.remote.response.MovieResponse
import com.roviery.core.domain.model.Movie
import com.roviery.core.domain.repository.IMovieRepository
import com.roviery.core.utils.AppExecutors
import com.roviery.core.utils.DataMapperMovie
import com.roviery.core.utils.DataMapperSearchMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllPopularMovie().map {
                    DataMapperMovie.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getPopularMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val listMovie = DataMapperMovie.mapResponsesToEntities(data)
                localDataSource.insertMovie(listMovie)
            }
        }.asFlow()

    override fun getFavoritePopularMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoritePopularMovie().map {
            DataMapperMovie.mapEntitiesToDomain(it)
        }
    }

    override fun searchMovie(query: String): Flow<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.searchMovie(query).map {
                    DataMapperSearchMovie.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.searchMovie(query)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val listSearchMovieEntity = DataMapperSearchMovie.mapResponsesToEntities(data)
                localDataSource.insertSearchMovie(listSearchMovieEntity)
            }
        }.asFlow()

    override fun setFavoritePopularMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapperMovie.mapDomainToEntity(movie)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoritePopularMovie(movieEntity, state) }
    }

}