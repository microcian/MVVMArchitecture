package com.abe.boilerplatemvvm.data.usecases

import com.abe.boilerplatemvvm.aide.utils.AppConstants
import com.abe.boilerplatemvvm.data.repository.ImagineRepository
import javax.inject.Inject

/**
 * A use-case to load the popular photos from Unsplash API.
 */
class FetchPopularPhotosUsecase @Inject constructor(private val repository: ImagineRepository) {
    suspend operator fun invoke(
            pageNum: Int = 1,
            pageSize: Int = AppConstants.ApiRequestParams.PHOTOS_PER_PAGE,
            orderBy: String = "popular"
    ) = repository.loadPhotos(
        pageNumber = pageNum,
        pageSize = pageSize,
        orderBy = orderBy
    )
}
