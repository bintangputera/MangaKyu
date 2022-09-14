package com.bintangpoetra.mangakyu.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintangpoetra.mangakyu.data.genre.model.response.GenreItem
import com.bintangpoetra.mangakyu.databinding.ItemGenreGridBinding

class GenreGridAdapter(private val listGenre: MutableList<GenreItem>): RecyclerView.Adapter<GenreGridAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = ItemGenreGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        listGenre[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = listGenre.size

    fun addAll(list: List<GenreItem>) {
        listGenre.addAll(list)
    }

    inner class GenreViewHolder(private val binding: ItemGenreGridBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(genreItem: GenreItem) {
            binding.apply {
                tvGenre.text = genreItem.name
            }
        }
    }
}