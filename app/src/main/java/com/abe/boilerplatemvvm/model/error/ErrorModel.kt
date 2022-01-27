package com.abe.boilerplatemvvm.model.error

data class ErrorModel(
    var statusCode: Int = 0,
    var errorCode: Int = 0,
    var message: String = ""
)