package com.thurainx.libraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.views.HomeView

class HomePresenterImpl: ViewModel(),HomePresenter {
    // view
    var mHomeView: HomeView? = null

    // model
    var mLibraryModel = LibraryModelImpl

    override fun initView(view: HomeView) {
        mHomeView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getBookLists {
            mHomeView?.showErrorMessage(it)
        }?.observe(owner){
            mHomeView?.showBookLists(it)
        }
    }

    override fun onTapBook(bookVO: BookVO) {
        Log.d("tap","tap book")
    }

    override fun onTapMoreBooks(bookList: BookListVO) {
        Log.d("tap","tap more books")

    }

    override fun onTapSearch() {
        mHomeView?.navigateToSearch()
    }
}