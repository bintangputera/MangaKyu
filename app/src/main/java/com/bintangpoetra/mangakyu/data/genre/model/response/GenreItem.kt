package com.bintangpoetra.mangakyu.data.genre.model.response

import com.google.gson.annotations.SerializedName

data class GenreItem(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String
)
