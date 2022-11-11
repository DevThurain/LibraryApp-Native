package com.thurainx.libraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.views.BookDetailView
import com.thurainx.libraryapp.mvp.views.HomeView

class BookDetailPresenterImpl : ViewModel(), BookDetailPresenter{
    // view
    var mBookDetailView: BookDetailView? = null

    // model
    var mLibraryModel = LibraryModelImpl


    override fun initView(view: BookDetailView) {
        mBookDetailView = view
    }

    override fun onUiReadyBookDetail(owner: LifecycleOwner, bookVO: BookVO) {
       mBookDetailView?.bindBookData(bookVO)

        mLibraryModel.insertRecentBookToDatabase(bookVO)

    }

    override fun onTapMoreRatings() {
        mBookDetailView?.navigateToRatingDetail()
    }

    override fun onTapBack() {
        mBookDetailView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {}


}