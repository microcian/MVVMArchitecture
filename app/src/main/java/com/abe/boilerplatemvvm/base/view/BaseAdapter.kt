package com.abe.boilerplatemvvm.base.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<BINDING : ViewDataBinding, HOLDER, VH : BaseViewHolder<BINDING>>
    (diffUtil: DiffUtil.ItemCallback<HOLDER>) : ListAdapter<HOLDER, BaseViewHolder<BINDING>>(diffUtil) {

    abstract fun getLayoutId(): Int

    abstract fun getInflater(): LayoutInflater

    abstract fun createViewHolder(binding: BINDING): BaseViewHolder<BINDING>

    private fun inflateView(parent: ViewGroup): BINDING {
        return DataBindingUtil.inflate(getInflater(), getLayoutId(), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        return createViewHolder(inflateView(parent))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        holder.bind(position)
    }
}