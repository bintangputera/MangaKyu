package com.bintangpoetra.mangakyu.presentation.manga.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bintangpoetra.mangakyu.data.lib.ApiResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.MangaItem
import com.bintangpoetra.mangakyu.databinding.FragmentMangaDetailBinding
import com.bintangpoetra.mangakyu.presentation.adapter.GenreGridAdapter
import com.bintangpoetra.mangakyu.utils.BundleKeys.BUNDLE_MANGA_ID
import com.bintangpoetra.mangakyu.utils.ext.click
import com.bintangpoetra.mangakyu.utils.ext.hide
import com.bintangpoetra.mangakyu.utils.ext.setImageUrl
import com.bintangpoetra.mangakyu.utils.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MangaDetailFragment : Fragment() {

    private val mangaDetailViewModel: MangaDetailViewModel by viewModels()

    private val genreGridAdapter: GenreGridAdapter by lazy { GenreGridAdapter(mutableListOf()) }

    private var _fragmentMangaDetailBinding: FragmentMangaDetailBinding? = null
    private val binding get() = _fragmentMangaDetailBinding!!

    private var mangaId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentMangaDetailBinding = FragmentMangaDetailBinding.inflate(inflater, container, false)
        return _fragmentMangaDetailBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initIntent()
        initAction()
        initProcess()
        initObservers()
    }

    private fun initIntent() {
        mangaId = arguments?.getInt(BUNDLE_MANGA_ID)!!
    }

    private fun initUI() {
        binding.apply {
            rvGenre.apply {
                val layoutManager = GridLayoutManager(context, 3)
                setLayoutManager(layoutManager)
            }
        }
    }

    private fun initAction() {
        binding.apply {
            toolbarDetail.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
            btnExpand.click {
                mcvSynopsis.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                mcvSynopsis.requestLayout()
                btnExpand.hide()
            }
        }
    }

    private fun initProcess() {
        mangaDetailViewModel.getMangaDetail(mangaId)
    }

    private fun initObservers() {
        mangaDetailViewModel.getMangaDetailResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    showToast("Loadinggg.....")
                }
                is ApiResponse.Success -> {
                    genreGridAdapter.addAll(result.data.data.genres)
                    initDetailData(result.data.data)
                }
                is ApiResponse.Error -> {
                    showToast(result.errorMessage)
                }
                else -> {}
            }
        }
    }

    private fun initDetailData(mangaItem: MangaItem) {
        binding.apply {
            imgPoster.setImageUrl(mangaItem.images?.jpg?.imageUrl.toString())
            tvMangaTitle.text = mangaItem.title
            tvChapter.text = mangaItem.chapters.toString()
            tvVolume.text = mangaItem.volumes.toString()
            tvSynopsis.text = mangaItem.synopsis
            tvRating.text = mangaItem.scored.toString()
            tvTitle.text = mangaItem.title

            val rawAuthorName = mangaItem.authors[0].name
            if (rawAuthorName.contains(",")) {
                val fixedAuthorName = rawAuthorName.split(",")
                val combineName = fixedAuthorName[1] + " " + fixedAuthorName[0]
                tvAuthor.text = combineName
                tvMangaAuthor.text = "By $combineName"
            }
            rvGenre.adapter = genreGridAdapter
        }
    }
}