package com.bintangpoetra.mangakyu.data.manga.model.response.detail

import com.bintangpoetra.mangakyu.data.manga.model.response.MangaItem
import com.google.gson.annotations.SerializedName

data class DetailMangaResponse(
    @SerializedName("data")
    val data: MangaItem,
    @SerializedName("status")
    val status: Int
)
