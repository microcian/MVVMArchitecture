package com.abe.boilerplatemvvm.adapters.viewHolders

import android.view.View
import com.abe.boilerplatemvvm.base.view.BaseViewHolder
import com.abe.boilerplatemvvm.databinding.TagItemLayoutBinding
import com.abe.boilerplatemvvm.model.tags.TagModel

class TagsViewHolder(
    view: TagItemLayoutBinding,
    val clickListener: (tagModel: TagModel) -> Unit
) : BaseViewHolder<TagItemLayoutBinding>(view) {

    private lateinit var tag: TagModel

    override fun bindData(data: Any, position: Int) {

        if (data is TagModel) {
            tag = data
            binding().data = tag
        }
    }

    override fun onClick(v: View?) {
        clickListener(tag)
    }
}