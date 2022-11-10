package com.thurainx.libraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class GoogleBookVO(
    @SerializedName("volumeInfo")
    val volumeInfo: GoogleVolumeInfo?,
)
