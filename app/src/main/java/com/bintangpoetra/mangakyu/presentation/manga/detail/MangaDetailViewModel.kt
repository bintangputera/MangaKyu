package com.bintangpoetra.mangakyu.presentation.manga.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bintangpoetra.mangakyu.data.lib.ApiResponse
import com.bintangpoetra.mangakyu.data.manga.MangaRepository
import com.bintangpoetra.mangakyu.data.manga.model.response.detail.DetailMangaResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaDetailViewModel @Inject constructor(
    private val mangaRepository: MangaRepository
) : ViewModel() {

    val getMangaDetailResult: LiveData<ApiResponse<DetailMangaResponse>> by lazy { _getMangaDetailResult }
    private var _getMangaDetailResult = MutableLiveData<ApiResponse<DetailMangaResponse>>()

    fun getMangaDetail(id: Int) {
        viewModelScope.launch {
            mangaRepository.getMangaDetail(id).collect {
                _getMangaDetailResult.postValue(it)
            }
        }
    }

}