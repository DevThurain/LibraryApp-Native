package com.thurainx.libraryapp.data.models

import androidx.lifecycle.LiveData
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import io.reactivex.rxjava3.core.Observable

interface LibraryModel {

    // NY Book List
    fun getBookListsFromDatabase(
        onFail: (String) -> Unit
    ): LiveData<List<BookListVO>>?

    fun getBookListByListName(
        listName: String,
        onSuccess: (List<BookVO>) -> Unit,
        onFail: (String) -> Unit,
    )

    fun searchBook(query: String) : Observable<List<BookVO>>

    // Recent Book List
    fun insertRecentBookToDatabase(bookVO: BookVO)

    fun getRecentBookByNameFromDatabase(bookName: String): LiveData<BookVO>?

    fun getRecentBookByNameFromDatabaseOneTime(bookName: String): BookVO?


    fun getRecentBookListFromDatabase(): LiveData<List<BookVO>>?

    fun getRecentBookListFromDatabaseOneTime(): List<BookVO>?


    fun getAllRecentBookByCategory(category: String): LiveData<List<BookVO>>?

    fun getAllRecentBookByCategoryOneTime(category: String): List<BookVO>?


    fun deleteRecentBookByName(bookName: String)

    // Shelves
    fun insertShelfToDatabase(shelfVO: ShelfVO)

    fun getAllShelves() : LiveData<List<ShelfVO>>?

    fun insertShelfListToDatabase(shelfList: List<ShelfVO>)

    fun getShelfById(id: Long) : LiveData<ShelfVO?>?

    fun deleteShelf(id: Long)


}