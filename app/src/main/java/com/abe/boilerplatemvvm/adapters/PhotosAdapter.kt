package com.abe.boilerplatemvvm.adapters

import com.abe.boilerplatemvvm.R
import com.abe.boilerplatemvvm.adapters.viewHolders.PhotosViewHolder
import com.abe.boilerplatemvvm.base.view.BaseAdapter
import com.abe.boilerplatemvvm.base.view.BaseViewHolder
import com.abe.boilerplatemvvm.databinding.PhotoItemLayoutBinding
import com.abe.boilerplatemvvm.model.photos.PhotoModel

class PhotosAdapter(
    private val clickListener: (photoModel: PhotoModel) -> Unit
) : BaseAdapter<PhotoItemLayoutBinding>() {

    init {
        addData(ArrayList<PhotoModel>())
    }

    fun addPhotosList(data: List<PhotoModel>) {
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

//class PhotosAdapter(val onPhotoSelected: (photo: PhotoModel, position: Int) -> Unit) :
//    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {
//
//    private val photoItems: ArrayList<PhotoModel> = arrayListOf()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
//        val binding = PhotoItemLayoutBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return PhotoViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
//        holder.bind(photoItems[position], position)
//    }
//
//    override fun getItemCount() = photoItems.size
//
//    fun updateItems(photosList: List<PhotoModel>) {
//        photoItems.clear()
//        photoItems.addAll(photosList)
//        notifyDataSetChanged()
//    }
//
//    inner class PhotoViewHolder(private val itemBinding: PhotoItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//
//        fun bind(photoModel: PhotoModel, position: Int) {
//            itemBinding.apply {
//                imgPhoto.load(photoModel.urls.thumb) {
//                    placeholder(R.color.color_box_background)
//                    crossfade(true)
//                }
//
//                cardPhoto.setOnClickListener {
//                    onPhotoSelected(photoModel, position)
//                }
//            }
//        }
//    }
//}
