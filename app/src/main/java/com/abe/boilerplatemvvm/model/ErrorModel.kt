package com.abe.boilerplatemvvm.model

data class ErrorModel(
    var errorCode: Int = 0,
    var errorMessage: String
) {
    companion object {
        operator fun invoke(
            errorCode: Int? = null,
            errorMessage: String? = null
        ) = ErrorModel(
            errorCode ?: 0,
            errorMessage ?: ""
        )
    }
}
