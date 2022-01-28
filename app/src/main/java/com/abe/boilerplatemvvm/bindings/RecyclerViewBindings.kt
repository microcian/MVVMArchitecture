package com.abe.boilerplatemvvm.bindings

import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abe.boilerplatemvvm.adapters.PhotosAdapter
import com.abe.boilerplatemvvm.adapters.TagsAdapter
import com.abe.boilerplatemvvm.aide.utils.NestedScrollViewListener
import com.abe.boilerplatemvvm.base.BaseAdapter
import com.abe.boilerplatemvvm.model.tags.TagModel
import com.abe.boilerplatemvvm.viewmodel.PhotosViewModel
import com.nextbridge.roomdb.entities.PhotoEntityDB

@BindingAdapter("adapter")
fun RecyclerView.bindRecyclerViewAdapter(adapter: BaseAdapter<*>) {
    this.adapter = this.adapter ?: adapter

}

@BindingAdapter("photosPagination")
fun NestedScrollView.bindPhotoPagination(photosViewModel: PhotosViewModel) {
    NestedScrollViewListener(
        this,
        isLoading = {
            return@NestedScrollViewListener !photosViewModel.inLoadingState
        },
        loadMore = { photosViewModel.loadMorePhotos() }
    ).run {
        currentPage = 1
    }
}

@BindingAdapter("payloadTags")
fun RecyclerView.bindRecyclerTagsData(response: List<TagModel>?) {
    response?.let {
        val adapter = this.adapter as? TagsAdapter
        if (adapter?.totalCount == 0) {
            adapter.totalCount = response.size
            adapter.addNewsList(response)
        }
    }
}

@BindingAdapter("payloadPhotos")
fun RecyclerView.bindRecyclerPhotosData(response: List<PhotoEntityDB>?) {
    response?.let {
        val adapter = this.adapter as? PhotosAdapter
        adapter?.totalCount = response.size
        adapter?.addPhotosList(response)
    }
}