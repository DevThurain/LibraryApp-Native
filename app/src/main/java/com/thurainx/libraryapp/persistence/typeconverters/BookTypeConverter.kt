package com.thurainx.libraryapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thurainx.libraryapp.data.vos.BookVO

class BookTypeConverter {
    @TypeConverter
    fun toString(actorList: List<BookVO>?): String {
        return Gson().toJson(actorList)
    }

    @TypeConverter
    fun toBookList(jsonStr: String) : List<BookVO>?{
        val type = object : TypeToken<List<BookVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}