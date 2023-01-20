package com.example.movieappazi.uiModels.movie

data class ResponseState(
    var totalPage: Int = 0,
    var page: Int = 1,
    var previousPage: Int = page - 1,
    var nextPage: Int = page + 1,
    var isHasPreviousPage: Boolean = false,
    var isHasNextPage: Boolean = false,
)