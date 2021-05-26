package com.abe.boilerplatemvvm.view.main.photos.detail

sealed class PhotoDetailsUiState

object LoadingState : PhotoDetailsUiState()
object ContentState : PhotoDetailsUiState()
class ErrorState(val message: String) : PhotoDetailsUiState()
