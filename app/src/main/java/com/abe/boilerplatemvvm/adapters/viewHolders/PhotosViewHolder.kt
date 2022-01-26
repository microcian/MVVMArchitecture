package com.abe.boilerplatemvvm.adapters.viewHolders

import android.view.View
import com.abe.boilerplatemvvm.base.view.BaseViewHolder
import com.abe.boilerplatemvvm.databinding.PhotoItemLayoutBinding
import com.nextbridge.roomdb.entities.PhotoEntityDB

class PhotosViewHolder(
    view: PhotoItemLayoutBinding,
    val clickListener: (photoModel: PhotoEntityDB) -> Unit
) : BaseViewHolder<PhotoItemLayoutBinding>(view) {

    private lateinit var tag: PhotoEntityDB

    override fun bindData(data: Any, position: Int) {

        if (data is PhotoEntityDB) {
            tag = data
            binding().data = tag
        }
    }

    override fun onClick(v: View?) {
        clickListener(tag)
    }
}