package com.abe.boilerplatemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.abe.boilerplatemvvm.base.BaseViewModel
import com.abe.boilerplatemvvm.model.tags.TagModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TagsViewModel @Inject constructor() : BaseViewModel() {

    private var photoModelLiveData: MutableLiveData<List<TagModel>> =
        MutableLiveData<List<TagModel>>()

    fun initPhotoModel(photo: List<TagModel>) {
        photoModelLiveData.postValue(photo)
    }

    fun getAllTags() = photoModelLiveData
}
