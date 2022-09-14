package com.bintangpoetra.mangakyu.data.manga

import com.bintangpoetra.mangakyu.data.lib.ApiResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.UpcomingResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.detail.DetailMangaResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.popular.PopularMangaResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.recent.RecentMangaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaRepository @Inject constructor(private val source: MangaDataSource) {

    suspend fun getPopularMangas(): Flow<ApiResponse<PopularMangaResponse>> {
        return source.getPopularMangas().flowOn(Dispatchers.IO)
    }

    suspend fun getRecentMangas(): Flow<ApiResponse<RecentMangaResponse>> {
        return source.getRecentMangas().flowOn(Dispatchers.IO)
    }

    suspend fun getMangaDetail(id: Int): Flow<ApiResponse<DetailMangaResponse>> {
        return source.getMangaDetail(id).flowOn(Dispatchers.IO)
    }

    suspend fun getUpcomingManga(): Flow<ApiResponse<UpcomingResponse>> {
        return source.getUpcomingManga().flowOn(Dispatchers.IO)
    }

}