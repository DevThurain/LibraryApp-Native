package com.thurainx.libraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books")
data class BookVO(
    @ColumnInfo(name = "age_group")
    @SerializedName(value = "age_group")
    val ageGroup: String?,

    @ColumnInfo(name = "author")
    @SerializedName(value = "author")
    val author: String?,

    @ColumnInfo(name = "book_image")
    @SerializedName(value = "book_image")
    val bookImage:String?,

    @ColumnInfo(name = "contributor")
    @SerializedName(value = "contributor")
    val contributor:String?,

    @ColumnInfo(name = "created_date")
    @SerializedName(value = "created_date")
    val createdDate:String?,

    @ColumnInfo(name = "description")
    @SerializedName(value = "description")
    val description:String?,

    @ColumnInfo(name = "price")
    @SerializedName(value = "price")
    val price:String?,

    @ColumnInfo(name = "publisher")
    @SerializedName(value = "publisher")
    val publisher: String?,

    @ColumnInfo(name = "rank")
    @SerializedName(value = "rank")
    val rank: Int?,

    @ColumnInfo(name = "rank_last_week")
    @SerializedName(value = "rank_last_week")
    val rankLastWeek: Int?,

    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName(value = "title")
    var title: String,

    @ColumnInfo(name = "updated_date")
    @SerializedName(value = "updated_date")
    val updatedDate: String?,

    @ColumnInfo(name = "book_list_name")
    @SerializedName("book_list_name")
    var bookListName:String = "",

    @ColumnInfo(name = "date_millis")
    @SerializedName("date_millis")
    var dateMillis:Long = 0,

) {
}