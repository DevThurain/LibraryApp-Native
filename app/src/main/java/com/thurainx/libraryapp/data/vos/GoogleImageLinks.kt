package com.thurainx.libraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class GoogleImageLinks(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String?,

    @SerializedName("thumbnail")
    val thumbnail: String?,
)
