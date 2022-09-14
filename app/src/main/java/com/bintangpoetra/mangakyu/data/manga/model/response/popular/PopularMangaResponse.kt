package com.bintangpoetra.mangakyu.data.manga.model.response.popular

import com.bintangpoetra.mangakyu.data.manga.model.response.MangaItem
import com.google.gson.annotations.SerializedName

data class PopularMangaResponse(
    @SerializedName("data")
    val data: List<MangaItem>
)
