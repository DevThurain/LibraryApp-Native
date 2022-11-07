package com.thurainx.libraryapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thurainx.libraryapp.data.vos.BookVO

@Dao
interface RecentBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleBook(bookVO: BookVO)

    @Query("SELECT * FROM books")
    fun getRecentBookList(): LiveData<List<BookVO>>

    @Query("DELETE FROM books")
    fun deleteAllRecentBooks()



}