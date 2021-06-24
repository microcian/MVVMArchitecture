package com.abe.boilerplatemvvm.aide.utils

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NestedScrollViewListener(
    nestedScrollView: NestedScrollView,
    private val isLoading: () -> Boolean,
    private val loadMore: (Int) -> Unit,
) : NestedScrollView.OnScrollChangeListener {
    var currentPage: Int = 0

    init {
        nestedScrollView.setOnScrollChangeListener(this)
    }

    override fun onScrollChange(
        v: NestedScrollView,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
            loadMore(++currentPage)
        }
    }
}