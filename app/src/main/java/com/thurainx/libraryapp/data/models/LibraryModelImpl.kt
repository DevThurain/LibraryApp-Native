package com.thurainx.libraryapp.data.models

import android.util.Log
import androidx.lifecycle.LiveData
import com.thurainx.libraryapp.data.vos.BookListVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object LibraryModelImpl : BasedModel(), LibraryModel {


    override fun getBookLists(onFail: (String) -> Unit): LiveData<List<BookListVO>>? {
        mTheLibraryApi.getBookList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results.lists?.forEach {
                    Log.d("bookList",it.listName.toString())
                }
                response.results.lists?.let {
                    mLibraryDatabase?.bookListDao()?.insertAllBookList(bookList = it)
                }
            }, {
                it.localizedMessage?.let { it1 -> onFail(it1.toString()) }
            }
            )

        return mLibraryDatabase?.bookListDao()?.getAllBookList()
    }
}