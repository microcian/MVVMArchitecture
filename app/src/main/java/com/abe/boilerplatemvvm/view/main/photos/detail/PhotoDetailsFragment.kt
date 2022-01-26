package com.abe.boilerplatemvvm.view.main.photos.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abe.boilerplatemvvm.R
import com.abe.boilerplatemvvm.aide.utils.AppConstants.BundleArgs.KEY_PHOTO
import com.abe.boilerplatemvvm.base.view.BaseFragment
import com.abe.boilerplatemvvm.base.viewmodel.BaseViewModel
import com.abe.boilerplatemvvm.bindings.loadImageWithGlide
import com.abe.boilerplatemvvm.databinding.PhotoDetailsFragmentBinding
import com.abe.boilerplatemvvm.model.photos.PhotoModel
import com.abe.boilerplatemvvm.viewmodel.photos.detail.PhotoDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailsFragment : BaseFragment<PhotoDetailsFragmentBinding>() {

//    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> PhotoDetailsFragmentBinding
//        get() = PhotoDetailsFragmentBinding::inflate

    private val viewModel: PhotoDetailsViewModel by viewModels()
    override fun getViewModel(): BaseViewModel = viewModel

    override fun initFragment() {
        if (binding.lifecycleOwner == null) {
            binding.apply {
//                lifecycleOwner = this@NewsListFragment
//                viewModel = this@NewsListFragment.viewModel
//                adapter = newsAdapter
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.photo_details_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val photo = arguments?.getParcelable<PhotoModel>(KEY_PHOTO)
        if (photo == null) {
            findNavController().popBackStack()
            return
        }
        initObservations()
        viewModel.initPhotoModel(photo)
    }

    private fun initObservations() {
        viewModel.photoModelLiveData.observe(viewLifecycleOwner) { photo ->
            loadImageWithGlide(binding.photoView, photo.urls.full)
        }
    }
}
