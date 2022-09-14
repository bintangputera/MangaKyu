package com.bintangpoetra.mangakyu.data.author.response

import com.google.gson.annotations.SerializedName

data class AuthorItem(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("name")
    val name: String
)
