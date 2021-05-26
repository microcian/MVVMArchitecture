package com.abe.boilerplatemvvm.data.usecases

import com.abe.boilerplatemvvm.aide.utils.AppConstants
import com.abe.boilerplatemvvm.data.repository.ImagineRepository
import javax.inject.Inject

/**
 * A use-case to search photos from Unsplash API.
 */
class SearchPhotosUsecase @Inject constructor(private val repository: ImagineRepository) {
    suspend operator fun invoke(
        query: String,
        pageNum: Int = 1,
        pageSize: Int = AppConstants.API.PHOTOS_PER_PAGE
    ) = repository.searchPhotos(
        query = query,
        pageNumber = pageNum,
        pageSize = pageSize
    )
}
