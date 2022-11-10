package com.thurainx.libraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.views.*
import com.thurainx.libraryapp.utils.SortType
import okhttp3.internal.cacheGet

class MoreBooksPresenterImpl : ViewModel(), MoreBooksPresenter {
    // view
    private var mMoreBooksView: MoreBooksView? = null

    // model
    private var mLibraryModel = LibraryModelImpl



    override fun initView(view: MoreBooksView) {
        mMoreBooksView = view
    }

    override fun onUiReady(owner: LifecycleOwner, listName: String) {
        Log.d("list_name",listName)
        mLibraryModel.getBookListByListName(listName = listName, onSuccess = { bookList ->
            mMoreBooksView?.showBookLists(bookList)

        }, onFail = {
            mMoreBooksView?.showErrorMessage(it)
        })
    }


    override fun onUiReady(owner: LifecycleOwner) {}

    override fun onTapAddToShelf(bookVO: BookVO) {
        mMoreBooksView?.navigateToAddToShelf(bookVO)
    }

    override fun onTapAddToLibrary(bookVO: BookVO) {
        mLibraryModel.insertRecentBookToDatabase(bookVO)

    }

    override fun onTapBookInfo(bookVO: BookVO) {
        mMoreBooksView?.navigateToBookDetail(bookVO)
    }

    override fun onTapBack() {
        mMoreBooksView?.navigateBack()
    }

    override fun onTapBook(bookVO: BookVO) {
        mMoreBooksView?.navigateToBookDetail(bookVO)
    }

    override fun onTapMore(bookVO: BookVO) {
        mMoreBooksView?.showBookDetailDialog(bookVO)
    }


}