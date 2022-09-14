package com.bintangpoetra.mangakyu.data.manga.model.response.recent

import com.bintangpoetra.mangakyu.data.manga.model.response.MangaItem
import com.google.gson.annotations.SerializedName

data class RecentMangaResponse(
    @SerializedName("data")
    val data: List<MangaItem>
)
