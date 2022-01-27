package com.abe.boilerplatemvvm.view.main.photos

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abe.boilerplatemvvm.R
import com.abe.boilerplatemvvm.adapters.PhotosAdapter
import com.abe.boilerplatemvvm.adapters.TagsAdapter
import com.abe.boilerplatemvvm.aide.utils.AppConstants.BundleArgs.KEY_PHOTO
import com.abe.boilerplatemvvm.aide.utils.dismissKeyboard
import com.abe.boilerplatemvvm.aide.utils.showSnack
import com.abe.boilerplatemvvm.base.BaseFragment
import com.abe.boilerplatemvvm.base.BaseViewModel
import com.abe.boilerplatemvvm.databinding.PhotosFragmentBinding
import com.abe.boilerplatemvvm.model.tags.TagModel
import com.abe.boilerplatemvvm.viewmodel.PhotosViewModel
import com.abe.boilerplatemvvm.viewmodel.TagsViewModel
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<PhotosFragmentBinding>() {

    override fun getViewModel(): BaseViewModel = viewModel
    override fun getLayoutId(): Int = R.layout.photos_fragment

    private val viewModelTags: TagsViewModel by viewModels()
    private val viewModel: PhotosViewModel by viewModels()

    private val tagsAdapter by lazy {
        TagsAdapter { tagModel ->
            performSearch(tagModel.tagName)
        }
    }
    private val photosAdapter by lazy {
        PhotosAdapter { photoModel ->
            val bundle = bundleOf(KEY_PHOTO to photoModel)
            findNavController().navigate(
                R.id.action_homeFragment_to_photoDetailsFragment,
                bundle
            )
        }
    }

    override fun initFragment() {
        if (binding.lifecycleOwner == null) {
            binding.apply {
                lifecycleOwner = this@PhotosFragment
                viewModelPhotos = this@PhotosFragment.viewModel
                viewModelTags = this@PhotosFragment.viewModelTags
                adapterTags = tagsAdapter
                adapterPhotos = photosAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        initTags()
        initObservations()
    }

    private fun setupViews() {
        context?.let { ctx ->
            val flexBoxLayoutManager = FlexboxLayoutManager(ctx).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
                alignItems = AlignItems.STRETCH
            }
            binding.recyclerTags.layoutManager = flexBoxLayoutManager
            binding.recyclerTags.adapter = tagsAdapter

            // Input Text Search
            binding.inputSearchPhotos.setEndIconOnClickListener {
                binding.txtSearchPhotos.setText("")
                binding.lblPopular.text = getString(R.string.label_popular_text_str)
                viewModel.fetchPhotos(1)
            }

            binding.txtSearchPhotos.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    binding.txtSearchPhotos.dismissKeyboard()
                    performSearch(binding.txtSearchPhotos.text.toString())
                }
                false
            }
        }
    }

    private fun performSearch(query: String) {
        binding.txtSearchPhotos.setText(query)
        binding.lblPopular.text = getString(R.string.message_search_results_for_str, query)
        viewModel.searchPhotos(query)
    }

    private fun initObservations() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ErrorState -> {
                    binding.nestedScrollView.showSnack(
                        state.message,
                        getString(R.string.action_retry_str)
                    ) {
                        viewModel.retry()
                    }
                }
            }
        }
    }

    private fun initTags() {
        val tags = arrayListOf(
            TagModel(
                tagName = "Food",
                imageUrl = "https://www.helpguide.org/wp-content/uploads/fast-foods-candy-cookies-pastries-768.jpg"
            ),
            TagModel(
                tagName = "Cars",
                imageUrl = "https://i.dawn.com/primary/2019/03/5c8da9fc3e386.jpg"
            ),
            TagModel(
                tagName = "Cities",
                imageUrl = "https://news.mit.edu/sites/default/files/styles/news_article__image_gallery/public/images/201306/20130603150017-0_0.jpg?itok=fU2rLfB6"
            ),
            TagModel(
                tagName = "Mountains",
                imageUrl = "https://www.dw.com/image/48396304_101.jpg"
            ),
            TagModel(
                tagName = "People",
                imageUrl = "https://cdn.lifehack.org/wp-content/uploads/2015/02/what-makes-people-happy.jpeg"
            ),
            TagModel(
                tagName = "Work",
                imageUrl = "https://www.plays-in-business.com/wp-content/uploads/2015/05/successful-business-meeting.jpg"
            ),
            TagModel(
                tagName = "Fashion",
                imageUrl = "https://www.remixmagazine.com/assets/Prada-SS21-1__ResizedImageWzg2Niw1NzZd.jpg"
            ),
            TagModel(
                tagName = "Animals",
                imageUrl = "https://kids.nationalgeographic.com/content/dam/kids/photos/animals/Mammals/A-G/cheetah-mom-cubs.adapt.470.1.jpg"
            ),
            TagModel(
                tagName = "Interior",
                imageUrl = "https://images.homify.com/c_fill,f_auto,q_0,w_740/v1495001963/p/photo/image/2013905/CAM_2_OPTION_1.jpg"
            )
        )
        viewModelTags.initPhotoModel(tags)
    }
}
