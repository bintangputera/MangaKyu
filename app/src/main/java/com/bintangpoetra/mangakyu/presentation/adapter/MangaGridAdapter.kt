package com.bintangpoetra.mangakyu.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintangpoetra.mangakyu.data.manga.model.response.MangaItem
import com.bintangpoetra.mangakyu.databinding.ItemMangaGridBinding
import com.bintangpoetra.mangakyu.utils.ext.click
import com.bintangpoetra.mangakyu.utils.ext.setImageUrl

class MangaGridAdapter(
    private val mangaList: MutableList<MangaItem>,
    private val isHome: Boolean,
    private val onClick: (MangaItem) -> Unit
) :
    RecyclerView.Adapter<MangaGridAdapter.MangaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = ItemMangaGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        mangaList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = if (isHome) 6 else mangaList.size

    fun addAll(list: List<MangaItem>) {
        mangaList.addAll(list)
    }

    inner class MangaViewHolder(private val binding: ItemMangaGridBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mangaItem: MangaItem) {
            val genreName = mutableListOf<String>()
            binding.apply {
                mangaItem.genres.forEach {
                    genreName.add(it.name)
                }
                tvMangaGenre.text = genreName.toString()
                tvMangaTitle.text = mangaItem.title
                imgPoster.setImageUrl(mangaItem.images?.jpg?.imageUrl.toString())

                root.click {
                    onClick.invoke(mangaItem)
                }
            }
        }
    }
}