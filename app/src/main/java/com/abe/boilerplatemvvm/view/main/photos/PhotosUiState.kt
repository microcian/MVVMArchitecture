package com.abe.boilerplatemvvm.view.main.photos

sealed class PhotosUiState

object LoadingState : PhotosUiState()
object LoadingNextPageState : PhotosUiState()
object ContentState : PhotosUiState()
object ContentNextPageState : PhotosUiState()
object EmptyState : PhotosUiState()
class ErrorState(val message: String) : PhotosUiState()
class ErrorNextPageState(val message: String) : PhotosUiState()
