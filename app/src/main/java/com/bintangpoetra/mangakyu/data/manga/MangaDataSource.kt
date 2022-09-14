package com.bintangpoetra.mangakyu.data.manga

import com.bintangpoetra.mangakyu.data.lib.ApiResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.UpcomingResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.detail.DetailMangaResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.popular.PopularMangaResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.recent.RecentMangaResponse
import com.bintangpoetra.mangakyu.data.manga.remote.MangaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaDataSource @Inject constructor(private val service: MangaService) {

    suspend fun getPopularMangas(): Flow<ApiResponse<PopularMangaResponse>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = service.getPopularMangas()
                if (response.data.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex.message.toString()))
            }
        }
    }

    suspend fun getRecentMangas(): Flow<ApiResponse<RecentMangaResponse>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = service.getRecentMangas()
                if (response.data.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex.message.toString()))
            }
        }
    }

    suspend fun getMangaDetail(id: Int): Flow<ApiResponse<DetailMangaResponse>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = service.getMangaDetail(id)
                emit(ApiResponse.Success(response))
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex.message.toString()))
            }
        }
    }

    suspend fun getUpcomingManga(): Flow<ApiResponse<UpcomingResponse>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = service.getUpcomingManga()
                if (response.data.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex.message.toString()))
            }
        }
    }

}