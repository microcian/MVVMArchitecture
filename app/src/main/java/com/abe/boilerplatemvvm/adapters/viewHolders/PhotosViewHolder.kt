package com.abe.boilerplatemvvm.adapters.viewHolders

import android.view.View
import com.abe.boilerplatemvvm.base.view.BaseViewHolder
import com.abe.boilerplatemvvm.databinding.PhotoItemLayoutBinding
import com.abe.boilerplatemvvm.model.photos.PhotoModel

class PhotosViewHolder(
    view: PhotoItemLayoutBinding,
    val clickListener: (photoModel: PhotoModel) -> Unit
) : BaseViewHolder<PhotoItemLayoutBinding>(view) {

    private lateinit var tag: PhotoModel

    override fun bindData(data: Any, position: Int) {

        if (data is PhotoModel) {
            tag = data
            binding().data = tag
        }
    }

    override fun onClick(v: View?) {
        clickListener(tag)
    }
}