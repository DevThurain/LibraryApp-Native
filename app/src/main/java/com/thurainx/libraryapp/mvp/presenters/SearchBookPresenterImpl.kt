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
import io.reactivex.rxjava3.core.Observable
import okhttp3.internal.cacheGet

class SearchBookPresenterImpl : ViewModel(), SearchBookPresenter {
    // view
    private var mSearchBookView: SearchBookView? = null

    // model
    private var mLibraryModel = LibraryModelImpl



    override fun initView(view: SearchBookView) {
        mSearchBookView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapBook(bookVO: BookVO) {
        mSearchBookView?.navigateToBookDetail(bookVO)

    }

    override fun onTapMore(bookVO: BookVO) {
        mSearchBookView?.showBookDetailDialog(bookVO)
    }


    override fun onTapBack() {
        mSearchBookView?.navigateBack()
    }


    override fun onTapAddToShelf(bookVO: BookVO) {
        mSearchBookView?.navigateToAddToShelf(bookVO)
    }

    override fun onTapAddToLibrary(bookVO: BookVO) {
        mLibraryModel.insertRecentBookToDatabase(bookVO)
    }

    override fun onTapBookInfo(bookVO: BookVO) {
        mSearchBookView?.navigateToBookDetail(bookVO)
    }


}