package com.abe.boilerplatemvvm.base.view

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<BINDING : ViewDataBinding>(binding: BINDING) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(position: Int)
}