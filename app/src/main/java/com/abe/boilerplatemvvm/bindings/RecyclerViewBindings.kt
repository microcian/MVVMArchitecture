package com.abe.boilerplatemvvm.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abe.boilerplatemvvm.adapters.TagsAdapter
import com.abe.boilerplatemvvm.base.view.BaseAdapter
import com.abe.boilerplatemvvm.model.tags.TagModel

@BindingAdapter("adapter")
fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: BaseAdapter<*>) {
    recyclerView.adapter = recyclerView.adapter ?: adapter

}

//@BindingAdapter("newsPagination")
//fun bindNewPagination(recyclerView: RecyclerView, newsViewModel: NewsViewModel) {
//    Paginator(
//        recyclerView,
//        isLoading = {
//            return@Paginator newsViewModel.outcomeLiveData.value is Result.Loading
//        },
//        loadMore = { newsViewModel.fetchAllNews(it) }
//    ).run {
//        currentPage = 1
//    }
//}

@BindingAdapter("payload")
fun bindRecyclerData(recyclerView: RecyclerView, response: List<TagModel>?) {
    response?.let {
        val adapter = recyclerView.adapter as? TagsAdapter
        adapter?.totalCount = response.size
        adapter?.addNewsList(response)
    }

}