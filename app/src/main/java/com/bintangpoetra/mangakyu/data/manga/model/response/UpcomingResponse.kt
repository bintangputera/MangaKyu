package com.bintangpoetra.mangakyu.data.manga.model.response

import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("data")
    val data: List<MangaItem>
)
