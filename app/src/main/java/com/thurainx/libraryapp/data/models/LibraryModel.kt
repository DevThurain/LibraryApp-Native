package com.thurainx.libraryapp.data.models

import androidx.lifecycle.LiveData
import com.thurainx.libraryapp.data.vos.BookListVO
import io.reactivex.rxjava3.core.Observable

interface LibraryModel {

     fun getBookLists(
        onFail : (String) -> Unit
    ): LiveData<List<BookListVO>>?


}