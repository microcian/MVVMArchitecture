package com.abe.boilerplatemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.abe.boilerplatemvvm.base.BaseViewModel
import com.nextbridge.roomdb.entities.PhotoEntityDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor() : BaseViewModel() {

    var photoModel: MutableLiveData<PhotoEntityDB> = MutableLiveData<PhotoEntityDB>()

    fun initPhotoModel(photo: PhotoEntityDB) {
        photoModel.value = photo
    }
}
