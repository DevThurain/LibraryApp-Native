package com.thurainx.libraryapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO

@Dao
interface BookListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBookList(bookList :List<BookListVO>)

    @Query("SELECT * FROM bookList")
    fun getAllBookList(): LiveData<List<BookListVO>>

    @Query("DELETE FROM bookList")
    fun deleteAllBookList()



}