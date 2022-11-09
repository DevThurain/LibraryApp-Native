package com.thurainx.libraryapp.data.models

import android.util.Log
import androidx.lifecycle.LiveData
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.utils.DateUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

object LibraryModelImpl : BasedModel(), LibraryModel {


    override fun getBookListsFromDatabase(onFail: (String) -> Unit): LiveData<List<BookListVO>>? {
        mTheLibraryApi.getBookList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results.lists?.forEach { bookList ->
                    bookList.books?.forEach { book ->
                        book.bookListName = bookList.listName.toString()
                    }
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

    // recent book
    override fun insertRecentBookToDatabase(bookVO: BookVO) {
        bookVO.dateMillis = DateUtils.convertDateStringToMilli(bookVO.createdDate.toString())
        mLibraryDatabase?.recentBookDao()?.insertSingleBook(bookVO)
    }

    override fun getRecentBookByNameFromDatabase(bookName: String): LiveData<BookVO>? {
        return mLibraryDatabase?.recentBookDao()?.getRecentBookByName(bookName)
    }

    override fun getRecentBookListFromDatabase(): LiveData<List<BookVO>>? {
        return mLibraryDatabase?.recentBookDao()?.getRecentBookList()
    }

    override fun getRecentBookListFromDatabaseOneTime(): List<BookVO>? {
        return mLibraryDatabase?.recentBookDao()?.getRecentBookListOneTime()
    }

    override fun getAllRecentBookByCategory(category: String): LiveData<List<BookVO>>? {
        return mLibraryDatabase?.recentBookDao()?.getAllRecentBookByCategory(category)

    }

    override fun getAllRecentBookByCategoryOneTime(category: String): List<BookVO>? {
        return mLibraryDatabase?.recentBookDao()?.getAllRecentBookByCategoryOneTime(category)
    }

    override fun deleteRecentBookByName(bookName: String) {
        mLibraryDatabase?.recentBookDao()?.deleteRecentBookByName(bookName)
    }

    override fun insertShelfToDatabase(shelfVO: ShelfVO) {
         mLibraryDatabase?.shelfDao()?.insertShelf(shelfVO)
    }

    override fun getAllShelves(): LiveData<List<ShelfVO>>? {
        return mLibraryDatabase?.shelfDao()?.getAllShelves()
    }

}