package com.abe.boilerplatemvvm.viewmodel.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abe.boilerplatemvvm.base.viewmodel.BaseViewModel
import com.abe.boilerplatemvvm.data.DataState
import com.abe.boilerplatemvvm.data.usecases.FetchPopularPhotosUsecase
import com.abe.boilerplatemvvm.data.usecases.SearchPhotosUsecase
import com.abe.boilerplatemvvm.model.photos.PhotoModel
import com.abe.boilerplatemvvm.view.main.photos.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
        private val fetchPopularPhotosUseCase: FetchPopularPhotosUsecase,
        private val searchPhotosUseCase: SearchPhotosUsecase
) : BaseViewModel() {

    private var _uiState = MutableLiveData<PhotosUiState>()
    var uiStateLiveData: LiveData<PhotosUiState> = _uiState

    private var _photosList = MutableLiveData<List<PhotoModel>>()
    var photosListLiveData: LiveData<List<PhotoModel>> = _photosList

    private var pageNumber = 1
    private var searchQuery: String = ""

    init {
        fetchPhotos(pageNumber)
    }

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
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            fetchPopularPhotosUseCase(page).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _photosList.postValue(dataState.data)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            val currentList = arrayListOf<PhotoModel>()
                            _photosList.value?.let { currentList.addAll(it) }
                            currentList.addAll(dataState.data)
                            _photosList.postValue(currentList)
                        }
                    }

                    is DataState.Error -> {
                        if (page == 1) {
                            _uiState.postValue(ErrorState(dataState.message))
                        } else {
                            _uiState.postValue(ErrorNextPageState(dataState.message))
                        }
                    }
                }
            }
        }
    }

    private fun searchPhotos(query: String, page: Int) {
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            searchPhotosUseCase(query, page).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _photosList.postValue(dataState.data)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            val currentList = arrayListOf<PhotoModel>()
                            _photosList.value?.let { currentList.addAll(it) }
                            currentList.addAll(dataState.data)
                            _photosList.postValue(currentList)
                        }
                    }

                    is DataState.Error -> {
                        if (page == 1) {
                            _uiState.postValue(ErrorState(dataState.message))
                        } else {
                            _uiState.postValue(ErrorNextPageState(dataState.message))
                        }
                    }
                }
            }
        }
    }
}
