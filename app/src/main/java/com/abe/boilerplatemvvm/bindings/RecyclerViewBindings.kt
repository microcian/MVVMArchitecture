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
fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: BaseAdapter<*>) {
    recyclerView.adapter = recyclerView.adapter ?: adapter

}

@BindingAdapter("photosPagination")
fun bindPhotoPagination(nestedScrollView: NestedScrollView, photosViewModel: PhotosViewModel) {
    NestedScrollViewListener(
        nestedScrollView,
        isLoading = {
            return@NestedScrollViewListener !photosViewModel.inLoadingState
        },
        loadMore = { photosViewModel.loadMorePhotos() }
    ).run {
        currentPage = 1
    }
}

@BindingAdapter("payloadTags")
fun bindRecyclerTagsData(recyclerView: RecyclerView, response: List<TagModel>?) {
    response?.let {
        val adapter = recyclerView.adapter as? TagsAdapter
        if (adapter?.totalCount == 0) {
            adapter.totalCount = response.size
            adapter.addNewsList(response)
        }
    }
}

@BindingAdapter("payloadPhotos")
fun bindRecyclerPhotosData(recyclerView: RecyclerView, response: List<PhotoEntityDB>?) {
    response?.let {
        val adapter = recyclerView.adapter as? PhotosAdapter
        adapter?.totalCount = response.size
        adapter?.addPhotosList(response)
    }
}