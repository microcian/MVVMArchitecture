package com.abe.boilerplatemvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abe.boilerplatemvvm.base.BaseViewModel
import com.abe.boilerplatemvvm.view.main.photos.detail.PhotoDetailsUiState
import com.nextbridge.roomdb.entities.PhotoEntityDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor() : BaseViewModel() {

    private var _uiState = MutableLiveData<PhotoDetailsUiState>()
    var uiStateLiveData: LiveData<PhotoDetailsUiState> = _uiState

    private var _photoModel = MutableLiveData<PhotoEntityDB>()
    var photoModelLiveData: LiveData<PhotoEntityDB> = _photoModel

    fun initPhotoModel(photo: PhotoEntityDB) {
        _photoModel.value = photo
    }
}
