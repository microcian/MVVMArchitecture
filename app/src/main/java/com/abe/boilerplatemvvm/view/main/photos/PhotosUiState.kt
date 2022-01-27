package com.abe.boilerplatemvvm.view.main.photos

sealed class PhotosUiState

class ErrorState(val message: String) : PhotosUiState()
