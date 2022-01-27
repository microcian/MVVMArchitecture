package com.abe.boilerplatemvvm.view.main.photos.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abe.boilerplatemvvm.R
import com.abe.boilerplatemvvm.aide.utils.AppConstants.BundleArgs.KEY_PHOTO
import com.abe.boilerplatemvvm.base.BaseFragment
import com.abe.boilerplatemvvm.base.BaseViewModel
import com.abe.boilerplatemvvm.bindings.loadImageWithGlide
import com.abe.boilerplatemvvm.databinding.PhotoDetailsFragmentBinding
import com.abe.boilerplatemvvm.viewmodel.PhotoDetailsViewModel
import com.nextbridge.roomdb.entities.PhotoEntityDB
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailsFragment : BaseFragment<PhotoDetailsFragmentBinding>() {

    private val viewModel: PhotoDetailsViewModel by viewModels()
    override fun getViewModel(): BaseViewModel = viewModel
    override fun getLayoutId(): Int = R.layout.photo_details_fragment

    override fun initFragment() {
        if (binding.lifecycleOwner == null) {
            binding.apply {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photo = arguments?.getParcelable<PhotoEntityDB>(KEY_PHOTO)
        if (photo == null) {
            findNavController().popBackStack()
            return
        }
        initObservations()
        viewModel.initPhotoModel(photo)
    }

    private fun initObservations() {
        viewModel.photoModel.observe(viewLifecycleOwner) { photo ->
            photo.urls?.run {
                full?.let {
                    loadImageWithGlide(binding.photoView, it)
                }
            }
        }
    }
}
