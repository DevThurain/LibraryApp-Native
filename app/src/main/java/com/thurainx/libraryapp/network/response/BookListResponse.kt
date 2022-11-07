package com.thurainx.libraryapp.network.response

import com.google.gson.annotations.SerializedName
import com.thurainx.libraryapp.data.vos.BookResultVO

data class BookListResponse(
    @SerializedName(value = "status")
    val status: String?,

    @SerializedName(value = "results")
    val results: BookResultVO
)
