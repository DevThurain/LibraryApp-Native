package com.thurainx.libraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.thurainx.libraryapp.persistence.typeconverters.BookTypeConverter


data class MoreBookListVO(

    @SerializedName("list_name_encoded")
    val listNameEncoded: String?,

    @SerializedName("list_name")
    val listName: String?,

    @SerializedName("book_details")
    val bookDetails: List<BookVO>?,
) {
}