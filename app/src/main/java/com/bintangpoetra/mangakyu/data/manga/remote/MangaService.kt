package com.bintangpoetra.mangakyu.data.manga.remote

import com.bintangpoetra.mangakyu.data.manga.model.response.UpcomingResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.detail.DetailMangaResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.popular.PopularMangaResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.recent.RecentMangaResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaService {

    @GET("top/manga")
    suspend fun getPopularMangas(): PopularMangaResponse

    @GET("manga")
    suspend fun getRecentMangas(): RecentMangaResponse

    @GET("manga/{id}")
    suspend fun getMangaDetail(
        @Path("id") id: Int
    ): DetailMangaResponse

    @GET("seasons/upcoming")
    suspend fun getUpcomingManga(): UpcomingResponse

}