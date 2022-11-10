package com.thurainx.libraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class BookResultVO(
    @SerializedName(value = "bestsellers_date")
    val bestsellersDate: String?,

    @SerializedName(value = "published_date")
    val publishedDate: String?,

    @SerializedName(value = "lists")
    val lists: List<BookListVO>?,


)
