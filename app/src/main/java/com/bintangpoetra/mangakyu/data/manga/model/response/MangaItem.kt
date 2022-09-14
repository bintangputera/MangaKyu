package com.bintangpoetra.mangakyu.data.manga.model.response

import com.bintangpoetra.mangakyu.data.author.response.AuthorItem
import com.bintangpoetra.mangakyu.data.genre.model.response.GenreItem
import com.google.gson.annotations.SerializedName

data class MangaItem(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("images")
    val images: MangaImagesItem?,
    @SerializedName("title")
    val title: String,
    @SerializedName("chapters")
    val chapters: Int,
    @SerializedName("volumes")
    val volumes: Int,
    @SerializedName("scored")
    val scored: Double,
    @SerializedName("published")
    val published: MangaPublishedItem,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("authors")
    val authors: List<AuthorItem>,
    @SerializedName("genres")
    val genres: List<GenreItem>
)

data class MangaImagesItem(
    @SerializedName("jpg")
    val jpg: MangaImagesDetailItem?
)

data class MangaImagesDetailItem(
    @SerializedName("image_url")
    val imageUrl: String?,
)

data class MangaPublishedItem(
    @SerializedName("prop")
    val prop: MangaPublishedPropItem
)

data class MangaPublishedPropItem(
    @SerializedName("from")
    val from: MangaPublishedPropDateItem
)

data class MangaPublishedPropDateItem(
    @SerializedName("day")
    val day: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("year")
    val year: Int
)
