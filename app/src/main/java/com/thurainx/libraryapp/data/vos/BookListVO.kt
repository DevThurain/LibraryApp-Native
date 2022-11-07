package com.thurainx.libraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.thurainx.libraryapp.persistence.typeconverters.BookTypeConverter

@Entity(tableName = "bookList")
@TypeConverters(
    BookTypeConverter::class
)
data class BookListVO(
    @PrimaryKey
    @ColumnInfo(name = "list_id")
    @SerializedName("list_id")
    val listId: Int,

    @ColumnInfo(name = "list_name")
    @SerializedName("list_name")
    val listName: String?,

    @ColumnInfo(name = "books")
    @SerializedName("books")
    val books: List<BookVO>?,
) {
}