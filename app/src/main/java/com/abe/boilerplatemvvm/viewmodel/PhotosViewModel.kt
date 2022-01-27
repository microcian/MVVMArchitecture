package com.abe.boilerplatemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abe.boilerplatemvvm.base.BaseViewModel
import com.abe.boilerplatemvvm.data.DataState
import com.abe.boilerplatemvvm.data.usecases.FetchPopularPhotosUsecase
import com.abe.boilerplatemvvm.data.usecases.SearchPhotosUsecase
import com.abe.boilerplatemvvm.view.main.photos.ErrorState
import com.abe.boilerplatemvvm.view.main.photos.PhotosUiState
import com.nextbridge.roomdb.entities.PhotoEntityDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val fetchPopularPhotosUseCase: FetchPopularPhotosUsecase,
    private val searchPhotosUseCase: SearchPhotosUsecase
) : BaseViewModel() {

    var uiState = MutableLiveData<PhotosUiState>()

    private var photosListLiveData: MutableLiveData<List<PhotoEntityDB>?> =
        MutableLiveData<List<PhotoEntityDB>?>()

    private var pageNumber = 1
    private var searchQuery: String = ""

    init {
        fetchPhotos(pageNumber)
    }

    fun getAllPhotos() = photosListLiveData

    fun loadMorePhotos() {
        pageNumber++
        if (searchQuery == "")
            fetchPhotos(pageNumber)
        else
            searchPhotos(searchQuery, pageNumber)
    }

    fun retry() {
        if (searchQuery == "")
            fetchPhotos(pageNumber)
        else
            searchPhotos(searchQuery, pageNumber)
    }

    fun searchPhotos(query: String) {
        searchQuery = query
        pageNumber = 1
        searchPhotos(query, pageNumber)
    }

    fun fetchPhotos(page: Int) {
        showLoaderPagination(page)
        viewModelScope.launch {
            fetchPopularPhotosUseCase(page).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        hideLoader()
                        photosListLiveData.postValue(dataState.data)
                    }
                    is DataState.Error -> {
                        showError()
                        uiState.postValue(ErrorState(dataState.message))
                        photosListLiveData.postValue(dataState.data)
                    }
                }
            }
        }
    }

    private fun searchPhotos(query: String, page: Int) {
        showLoaderPagination(page)
        viewModelScope.launch {
            searchPhotosUseCase(query, page).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        hideLoader()
                        if (page == 1) {
                            // First page
                            photosListLiveData.postValue(dataState.data)
                        } else {
                            // Any other page
                            val currentList = arrayListOf<PhotoEntityDB>()
                            photosListLiveData.value?.let { currentList.addAll(it) }
                            dataState.data?.let {
                                currentList.addAll(it)
                                photosListLiveData.postValue(currentList)
                            }
                        }
                    }

                    is DataState.Error -> {
                        showError()
                        uiState.postValue(ErrorState(dataState.message))
                    }
                }
            }
        }
    }
}
