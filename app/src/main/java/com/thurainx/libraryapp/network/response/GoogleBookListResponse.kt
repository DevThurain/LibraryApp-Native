package com.thurainx.libraryapp.network.response

import com.google.gson.annotations.SerializedName
import com.thurainx.libraryapp.data.vos.BookResultVO
import com.thurainx.libraryapp.data.vos.GoogleBookVO

data class GoogleBookListResponse(
    @SerializedName(value = "items")
    val items: List<GoogleBookVO>?,

)
