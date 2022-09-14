package com.bintangpoetra.mangakyu.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintangpoetra.mangakyu.data.manga.model.response.MangaItem
import com.bintangpoetra.mangakyu.databinding.ItemMangaCardBinding
import com.bintangpoetra.mangakyu.utils.ext.setImageUrl

class MangaCardAdapter(private val mangaList: MutableList<MangaItem>): RecyclerView.Adapter<MangaCardAdapter.MangaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = ItemMangaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        mangaList[position].let {
            holder.bind(it)
        }
    }

    fun addAll(list: List<MangaItem>) {
        mangaList.addAll(list)
    }

    override fun getItemCount(): Int = 6

    inner class MangaViewHolder(private val binding: ItemMangaCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(mangaItem: MangaItem) {
            binding.apply {
                imgCoverThumbnail.setImageUrl(mangaItem.images?.jpg?.imageUrl.toString())

                tvMangaTitle.text = mangaItem.title
            }
        }
    }
}