package com.thurainx.libraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.thurainx.libraryapp.persistence.typeconverters.BookTypeConverter

@Entity(tableName = "shelves")
@TypeConverters(
    BookTypeConverter::class
)
data class ShelfVO(
    @PrimaryKey
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "books")
    @SerializedName("books")
    var books: List<BookVO> = listOf(),
){
}