package com.abe.boilerplatemvvm.adapters

import com.abe.boilerplatemvvm.R
import com.abe.boilerplatemvvm.adapters.viewHolders.PhotosViewHolder
import com.abe.boilerplatemvvm.base.view.BaseAdapter
import com.abe.boilerplatemvvm.base.view.BaseViewHolder
import com.abe.boilerplatemvvm.databinding.PhotoItemLayoutBinding
import com.nextbridge.roomdb.entities.PhotoEntityDB

class PhotosAdapter(
    private val clickListener: (photoModel: PhotoEntityDB) -> Unit
) : BaseAdapter<PhotoItemLayoutBinding>() {

    init {
        addData(ArrayList<PhotoEntityDB>())
    }

    fun addPhotosList(data: List<PhotoEntityDB>) {
        data.let {
            addData(it)
            notifyDataSetChanged()
        }
    }

    override fun viewHolder(
        layout: Int,
        binding: PhotoItemLayoutBinding
    ): BaseViewHolder<PhotoItemLayoutBinding> {
        return PhotosViewHolder(binding, clickListener)
    }

    override fun layout(position: Int): Int {
        return R.layout.photo_item_layout
    }
}