package com.abe.boilerplatemvvm.viewmodel.tags

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abe.boilerplatemvvm.base.BaseViewModel
import com.abe.boilerplatemvvm.model.tags.TagModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TagsViewModel @Inject constructor() : BaseViewModel() {

    private var _photoModel = MutableLiveData<List<TagModel>>()
    var photoModelLiveData: LiveData<List<TagModel>> = _photoModel

    fun initPhotoModel(photo: List<TagModel>) {
        _photoModel.postValue(photo)
    }

    fun getAllTags() = photoModelLiveData
}
