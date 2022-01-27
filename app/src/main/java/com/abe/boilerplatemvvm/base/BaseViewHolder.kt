package com.abe.boilerplatemvvm.base

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused", "LeakingThis")
abstract class BaseViewHolder<BINDING : ViewDataBinding>(private val view: BINDING) :
    RecyclerView.ViewHolder(view.root), View.OnClickListener {
    init {
        view.root.setOnClickListener(this)
    }

    abstract fun bindData(data: Any, position: Int)
    fun view() = view.root
    fun binding() = view
    fun context(): Context = view.root.context
}