package com.abe.boilerplatemvvm.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abe.boilerplatemvvm.aide.utils.Paginator
import com.abe.boilerplatemvvm.base.stateUI.StateViewModel
import com.abe.boilerplatemvvm.base.view.BaseAdapter


@BindingAdapter("adapter")
fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: BaseAdapter<*>) {
    recyclerView.adapter = recyclerView.adapter ?: adapter

}

//@BindingAdapter("newsPagination")
//fun bindNewPagination(recyclerView: RecyclerView, newsViewModel: NewsViewModel) {
//    Paginator(
//        recyclerView,
//        isLoading = {
//            return@Paginator newsViewModel.outcomeLiveData.value is StateViewModel.Loading
//        },
//        loadMore = { newsViewModel.fetchAllNews(it) }
//    ).run {
//        currentPage = 1
//    }
//}
//
//@BindingAdapter("payload")
//fun bindRecyclerData(recyclerView: RecyclerView, response: StateViewModel<NewsResponse>?) {
//    response?.let {
//        if (it is StateViewModel.Success) {
//            it.data.let { newsResponse ->
//                val adapter = recyclerView.adapter as? NewsAdapter
//                adapter?.totalCount = newsResponse.totalResults
//                adapter?.addNewsList(newsResponse.articles)
//            }
//        }
//    }
//}