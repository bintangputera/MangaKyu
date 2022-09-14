package com.bintangpoetra.mangakyu.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintangpoetra.mangakyu.data.manga.model.response.MangaItem
import com.bintangpoetra.mangakyu.databinding.ItemMangaCardBannerBinding
import com.bintangpoetra.mangakyu.utils.ext.click
import com.bintangpoetra.mangakyu.utils.ext.setImageUrl

class MangaCardBannerAdapter(private val list: MutableList<MangaItem>, private val onClick: (MangaItem) -> Unit) : RecyclerView.Adapter<MangaCardBannerAdapter.MangaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = ItemMangaCardBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(view);
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        list[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = 6

    fun addAll(mList: List<MangaItem>) {
        list.addAll(mList)
    }

    inner class MangaViewHolder(
        private val binding: ItemMangaCardBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mangaItem: MangaItem) {
            val genreName = mutableListOf<String>()
            binding.apply {
                mangaItem.genres.forEach {
                    genreName.add(it.name)
                }
                tvMangaGenre.text = genreName.toString()
                tvMangaTitle.text = mangaItem.title
                tvSynopsis.text = mangaItem.synopsis

                imgCoverThumbnail.setImageUrl(mangaItem.images?.jpg?.imageUrl.toString())
                imgPoster.setImageUrl(mangaItem.images?.jpg?.imageUrl.toString())

                root.click {
                    onClick.invoke(mangaItem)
                }
            }
        }
    }
}