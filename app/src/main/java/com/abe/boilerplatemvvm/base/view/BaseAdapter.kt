package com.abe.boilerplatemvvm.base.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<BINDING : ViewDataBinding> :
    RecyclerView.Adapter<BaseViewHolder<BINDING>>() {
    private val data = mutableListOf<Any>()
    var totalCount: Int = 0

    fun data(): MutableList<Any> {
        return data
    }

    fun <DATA> addData(data: List<DATA>) {
        data().addAll(ArrayList<Any>(data))
    }

    protected abstract fun viewHolder(
        @LayoutRes layout: Int,
        binding: BINDING
    ): BaseViewHolder<BINDING>

    protected abstract fun layout(position: Int): Int

    override fun onCreateViewHolder(
        parent: ViewGroup,
        @LayoutRes layout: Int
    ): BaseViewHolder<BINDING> {
        val view = inflateView(parent, layout)
        return viewHolder(layout, view)
    }

    override fun getItemCount(): Int {
        return data().size
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<BINDING>, position: Int) {
        val data = data()[position]
        try {
            viewHolder.bindData(data, position)
            viewHolder.binding().executePendingBindings()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return layout(position)
    }

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): BINDING {

        return DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            viewType,
            viewGroup,
            false
        )
    }
}