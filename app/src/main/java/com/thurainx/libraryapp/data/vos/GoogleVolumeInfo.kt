package com.thurainx.libraryapp.data.vos

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class GoogleVolumeInfo(
    @SerializedName("title")
    val title: String?,

    @SerializedName("authors")
    val authors: List<String>?,

    @SerializedName(value = "description")
    val description: String?,

    @SerializedName(value = "categories")
    val categories: List<String>?,

    @SerializedName(value = "imageLinks")
    val imageLinks: GoogleImageLinks?,

    @ColumnInfo(name = "date_millis")
    @SerializedName("date_millis")
    var dateMillis:Long = 0,
)
