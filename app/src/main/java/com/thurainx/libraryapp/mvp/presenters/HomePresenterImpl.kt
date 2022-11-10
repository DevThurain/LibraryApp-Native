package com.thurainx.libraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.utils.DateUtils

class HomePresenterImpl : ViewModel(), HomePresenter {
    // view
    var mHomeView: HomeView? = null

    // model
    var mLibraryModel = LibraryModelImpl

    override fun initView(view: HomeView) {
        mHomeView = view
    }



    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getBookListsFromDatabase {
            mHomeView?.showErrorMessage(it)
        }?.observe(owner) {
            mHomeView?.showBookLists(it)
        }

        mLibraryModel.getRecentBookListFromDatabase()
            ?.observe(owner){
                mHomeView?.showRecentBookList(it.reversed())
            }
    }

    override fun onTapBook(bookVO: BookVO) {
        mHomeView?.navigateToBookDetail(bookVO)
    }
    override fun onTapAddToShelf(bookVO: BookVO) {
        mHomeView?.navigateToAddToShelf(bookVO)
    }

    override fun onTapAddToLibrary(bookVO: BookVO) {
        mLibraryModel.insertRecentBookToDatabase(bookVO)
    }

    override fun onTapBookInfo(bookVO: BookVO) {
        mHomeView?.navigateToBookDetail(bookVO)
    }

    override fun onTapMore(bookVO: BookVO) {
        mHomeView?.showBookDetailDialog(bookVO)
    }

    override fun onTapMoreBooks(bookList: BookListVO) {
        mHomeView?.navigateToMoreBook(bookList)

    }

    override fun onTapSearch() {
        mHomeView?.navigateToSearch()
    }

    override fun onTapRecentBook(bookVO: BookVO) {
        mLibraryModel.insertRecentBookToDatabase(bookVO)
        mHomeView?.navigateToBookDetail(bookVO)
    }
}