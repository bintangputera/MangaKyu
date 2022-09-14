package com.bintangpoetra.mangakyu.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bintangpoetra.mangakyu.data.lib.ApiResponse
import com.bintangpoetra.mangakyu.data.manga.MangaRepository
import com.bintangpoetra.mangakyu.data.manga.model.response.UpcomingResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.popular.PopularMangaResponse
import com.bintangpoetra.mangakyu.data.manga.model.response.recent.RecentMangaResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mangaRepository: MangaRepository) : ViewModel() {

    val getPopularMangasResult: LiveData<ApiResponse<PopularMangaResponse>> by lazy { _getPopularMangasResult }
    private var _getPopularMangasResult = MutableLiveData<ApiResponse<PopularMangaResponse>>()

    val getRecentMangasResult: LiveData<ApiResponse<RecentMangaResponse>> by lazy { _getRecentMangasResult }
    private var _getRecentMangasResult = MutableLiveData<ApiResponse<RecentMangaResponse>>()

    val getUpcomingMangaResult: LiveData<ApiResponse<UpcomingResponse>> by lazy { _getUpcomingMangasResult }
    private var _getUpcomingMangasResult = MutableLiveData<ApiResponse<UpcomingResponse>>()

    fun getPopularManga() {
        viewModelScope.launch {
            mangaRepository.getPopularMangas()
                .collect {
                    _getPopularMangasResult.postValue(it)
                }
        }
    }

    fun getRecentManga() {
        viewModelScope.launch {
            mangaRepository.getRecentMangas()
                .collect {
                    _getRecentMangasResult.postValue(it)
                }
        }
    }

    fun getUpcomingManga() {
        viewModelScope.launch {
            mangaRepository.getUpcomingManga()
                .collect {
                    _getUpcomingMangasResult.postValue(it)
                }
        }
    }

}