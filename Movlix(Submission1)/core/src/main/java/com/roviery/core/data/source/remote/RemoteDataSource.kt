package com.roviery.core.data.source.remote

import android.util.Log
import com.roviery.core.BuildConfig
import com.roviery.core.data.source.remote.network.ApiResponse
import com.roviery.core.data.source.remote.network.ApiService
import com.roviery.core.data.source.remote.response.MovieResponse
import com.roviery.core.data.source.remote.response.TvShowResponse
import com.roviery.core.utils.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    // Movie

    suspend fun getPopularMovie(): Flow<ApiResponse<List<MovieResponse>>> {

        return flow {
            try {
                val response =
                    apiService.getPopularMovie(BuildConfig.API_KEY, Credentials.FIRST_PAGE)
                val dataArray = response.listMovie
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listMovie))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun searchMovie(query: String): Flow<ApiResponse<List<MovieResponse>>> {

        return flow {
            try {
                Log.d("RemoteDataSource", query)
                val response =
                    apiService.searchMovie(BuildConfig.API_KEY, query, Credentials.FIRST_PAGE)
                val dataArray = response.listMovie
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listMovie))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    // Tv Show

    suspend fun getPopularTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {

        return flow {
            try {
                val response =
                    apiService.getPopularTvShow(BuildConfig.API_KEY, Credentials.FIRST_PAGE)
                val dataArray = response.listTvShow
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listTvShow))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun searchTvShow(query: String): Flow<ApiResponse<List<TvShowResponse>>> {

        return flow {
            try {
                Log.d("RemoteDataSource", query)
                val response =
                    apiService.searchTvShow(BuildConfig.API_KEY, query, Credentials.FIRST_PAGE)
                val dataArray = response.listTvShow
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listTvShow))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

}