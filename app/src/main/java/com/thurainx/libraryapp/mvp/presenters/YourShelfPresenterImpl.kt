package com.thurainx.libraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.YourShelfView
import com.thurainx.libraryapp.utils.DateUtils

class YourShelfPresenterImpl : ViewModel(), YourShelfPresenter {
    // view
    var mShelfView: YourShelfView? = null

    // model
    var mLibraryModel = LibraryModelImpl

    override fun initView(view: YourShelfView) {
        mShelfView = view
    }



    override fun onUiReady(owner: LifecycleOwner) {
       mLibraryModel.getAllShelves()?.observe(owner){ shelfList ->
           mShelfView?.showShelfList(shelfList)
       }
    }

    override fun onTapShelf(shelfVO: ShelfVO) {
        mShelfView?.navigateToShelfDetail(shelfVO.uniqueId)
    }

    override fun onTapCreateShelf() {
        mShelfView?.navigateToCreateShelf()
    }

}