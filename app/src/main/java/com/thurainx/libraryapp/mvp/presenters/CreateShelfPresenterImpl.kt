package com.thurainx.libraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.views.CreateShelfView
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.YourShelfView
import com.thurainx.libraryapp.utils.DateUtils

class CreateShelfPresenterImpl : ViewModel(), CreateShelfPresenter {
    // view
    var mCreateShelfView: CreateShelfView? = null

    // model
    var mLibraryModel = LibraryModelImpl

    override fun initView(view: CreateShelfView) {
        mCreateShelfView = view
    }

    override fun onTapComplete(shelfName: String) {
        if(shelfName.isNotEmpty()){
            val shelfVO = ShelfVO(
                name = shelfName
            )
            mLibraryModel.insertShelfToDatabase(shelfVO)
            mCreateShelfView?.insertShelfComplete()
        }else{
            mCreateShelfView?.showErrorMessage("Shelf name cannot be empty.")
        }

    }

    override fun onTapBack() {
        mCreateShelfView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }



}