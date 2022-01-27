package com.abe.boilerplatemvvm.base

sealed class BaseUiState

object LoadingState : BaseUiState()
object LoadingNextPageState : BaseUiState()
object DismissLoaderState : BaseUiState()
class ErrorState(val message: String? = null) : BaseUiState()
