package com.thurainx.libraryapp.data.models

import androidx.lifecycle.LiveData
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import io.reactivex.rxjava3.core.Observable

interface LibraryModel {

    fun getBookListsFromDatabase(
        onFail: (String) -> Unit
    ): LiveData<List<BookListVO>>?

    fun insertRecentBookToDatabase(bookVO: BookVO)

    fun getRecentBookByNameFromDatabase(bookName: String): LiveData<BookVO>?

    fun getRecentBookListFromDatabase(): LiveData<List<BookVO>>?


}