package com.bintangpoetra.mangakyu.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintangpoetra.mangakyu.data.lib.ApiResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.MangaItem
import com.bintangpoetra.mangakyu.databinding.FragmentHomeBinding
import com.bintangpoetra.mangakyu.presentation.adapter.MangaCardAdapter
import com.bintangpoetra.mangakyu.presentation.adapter.MangaCardBannerAdapter
import com.bintangpoetra.mangakyu.presentation.adapter.MangaGridAdapter
import com.bintangpoetra.mangakyu.utils.ext.addSnapper
import com.bintangpoetra.mangakyu.utils.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private val mangaCardBannerAdapter: MangaCardBannerAdapter by lazy {
        MangaCardBannerAdapter(mutableListOf(), onClick = {
            goToDetail(it)
        })
    }

    private val mangaGridBannerAdapter: MangaGridAdapter by lazy {
        MangaGridAdapter(
            mutableListOf(),
            true,
            onClick = {
                goToDetail(it)
            }
        )
    }

    private val mangaCardAdapter: MangaCardAdapter by lazy {
        MangaCardAdapter(
            mutableListOf()
        )
    }

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentHomeBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(inflater)
        return _fragmentHomeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initProcess()
        initObservers()
    }

    private fun initUI() {
        binding.apply {
            rvPopularManga.apply {
                layoutManager = GridLayoutManager(context, 3)
            }
            rvNewReleaseManga.apply {
                addSnapper()
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            rvUpcomingManga.apply {
                addSnapper()
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun initProcess() {
        homeViewModel.getPopularManga()
        homeViewModel.getRecentManga()
        homeViewModel.getUpcomingManga()
    }

    private fun initObservers() {
        homeViewModel.getPopularMangasResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    showToast("Loadinggg")
                }
                is ApiResponse.Success -> {
                    mangaGridBannerAdapter.addAll(result.data.data)
                    mangaCardBannerAdapter.addAll(result.data.data)
                    binding.apply {
                        rvPopularManga.adapter = mangaGridBannerAdapter
                        rvNewReleaseManga.adapter = mangaCardBannerAdapter
                    }
                }
                is ApiResponse.Error -> {
                    showToast(result.errorMessage)
                }
                is ApiResponse.Empty -> {
                    showToast("Kosong ygyy")
                }
                else -> {}
            }
        }
        homeViewModel.getRecentMangasResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    showToast("Loadinggg")
                }
                is ApiResponse.Success -> {
                    mangaCardBannerAdapter.addAll(result.data.data)
                    binding.apply {
                        rvNewReleaseManga.adapter = mangaCardBannerAdapter
                    }
                }
                is ApiResponse.Error -> {
                    showToast(result.errorMessage)
                }
                is ApiResponse.Empty -> {
                    showToast("Kosong ygyy")
                }
                else -> {}
            }
        }
        homeViewModel.getUpcomingMangaResult.observe(viewLifecycleOwner) { result ->
            when(result) {
                is ApiResponse.Loading -> {
                    showToast("Loadinggg")
                }
                is ApiResponse.Success -> {
                    mangaCardAdapter.addAll(result.data.data)
                    binding.apply {
                        rvUpcomingManga.adapter = mangaCardAdapter
                    }
                }
                is ApiResponse.Error -> {
                    showToast(result.errorMessage)
                }
                is ApiResponse.Empty -> {
                    showToast("Kosong ygyy")
                }
                else -> {}
            }
        }
    }

    private fun goToDetail(mangaItem: MangaItem) {
        val action = HomeFragmentDirections.actionHomeFragmentToMangaDetailFragment(mangaItem.malId)
        findNavController().navigate(action)
    }
}