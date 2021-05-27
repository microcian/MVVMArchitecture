package com.abe.boilerplatemvvm.base.stateUI

sealed class BaseUiState

object LoadingState : BaseUiState()
object ContentState : BaseUiState()
object EmptyState : BaseUiState()
class ErrorState(val message: String) : BaseUiState()
