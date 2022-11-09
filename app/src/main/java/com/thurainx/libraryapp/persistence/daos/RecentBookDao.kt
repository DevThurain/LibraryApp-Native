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

    @Query("SELECT * FROM books WHERE title = :bookName")
    fun getRecentBookByName(bookName: String): LiveData<BookVO>

    @Query("SELECT * FROM books")
    fun getRecentBookList(): LiveData<List<BookVO>>

    @Query("SELECT * FROM books")
    fun getRecentBookListOneTime():List<BookVO>

    @Query("SELECT * FROM books WHERE book_list_name = :category")
    fun getAllRecentBookByCategory(category: String): LiveData<List<BookVO>>

    @Query("SELECT * FROM books WHERE book_list_name = :category")
    fun getAllRecentBookByCategoryOneTime(category: String): List<BookVO>


//
//    @Query("SELECT * FROM books ORDER BY title")
//    fun getAllRecentBookSortedByBookName(): LiveData<List<BookVO>>
//
//    @Query("SELECT * FROM books ORDER BY date_millis")
//    fun getAllRecentBookSortedByDate(): LiveData<List<BookVO>>
//
//
//    // with category
//    @Query("SELECT * FROM books WHERE book_list_name = :category")
//    fun getAllRecentBookByCategorySortedByRecent(category: String): LiveData<List<BookVO>>
//
//    @Query("SELECT * FROM books WHERE book_list_name = :category ORDER BY title")
//    fun getAllRecentBookByCategorySortedByBookName(category: String): LiveData<List<BookVO>>
//
//    @Query("SELECT * FROM books WHERE book_list_name = :category ORDER BY date_millis")
//    fun getAllRecentBookSByCategorySortedByDate(category: String): LiveData<List<BookVO>>


    @Query("DELETE FROM books")
    fun deleteAllRecentBooks()

    @Query("DELETE FROM books WHERE title = :name")
    fun deleteRecentBookByName(name: String)

}


