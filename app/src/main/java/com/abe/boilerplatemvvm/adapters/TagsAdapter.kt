package com.abe.boilerplatemvvm.adapters

import com.abe.boilerplatemvvm.R
import com.abe.boilerplatemvvm.adapters.viewHolders.TagsViewHolder
import com.abe.boilerplatemvvm.base.view.BaseAdapter
import com.abe.boilerplatemvvm.base.view.BaseViewHolder
import com.abe.boilerplatemvvm.databinding.TagItemLayoutBinding
import com.abe.boilerplatemvvm.model.tags.TagModel

class TagsAdapter(
    private val clickListener: (result: TagModel) -> Unit
) : BaseAdapter<TagItemLayoutBinding>() {

    init {
        addData(ArrayList<TagModel>())
    }

    fun addNewsList(data: List<TagModel>) {
        data.let {
            addData(it)
            notifyDataSetChanged()
        }
    }

    override fun viewHolder(
        layout: Int,
        binding: TagItemLayoutBinding
    ): BaseViewHolder<TagItemLayoutBinding> {
        return TagsViewHolder(binding, clickListener)
    }

    override fun layout(position: Int): Int {
        return R.layout.tag_item_layout
    }
}

//class TagsAdapter(val onTagSelected: (tag: TagModel, position: Int) -> Unit) :
//        RecyclerView.Adapter<TagsAdapter.TagViewHolder>() {
//
//    private val tagItems: ArrayList<TagModel> = arrayListOf()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
//        val binding = TagItemLayoutBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return TagViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
//        holder.bind(tagItems[position], position)
//    }
//
//    override fun getItemCount() = tagItems.size
//
//    fun updateItems(tagsList: List<TagModel>) {
//        tagItems.clear()
//        tagItems.addAll(tagsList)
//        notifyDataSetChanged()
//    }
//
//    inner class TagViewHolder(private val itemBinding: TagItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//
//        fun bind(tagModel: TagModel, position: Int) {
//            itemBinding.apply {
//                txtTagName.text = tagModel.tagName
//                imgTag.load(tagModel.imageUrl) {
//                    placeholder(R.color.color_box_background)
//                    crossfade(true)
//                }
//
//                cardTag.setOnClickListener {
//                    onTagSelected(tagModel, position)
//                }
//            }
//        }
//    }
//}
