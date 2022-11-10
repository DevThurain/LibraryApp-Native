package com.thurainx.libraryapp.network.response

import com.google.gson.annotations.SerializedName
import com.thurainx.libraryapp.data.vos.BookResultVO
import com.thurainx.libraryapp.data.vos.MoreBookListVO

data class MoreBooksResponse(
    @SerializedName(value = "status")
    val status: String?,

    @SerializedName(value = "results")
    val results: List<MoreBookListVO>
)
