package com.thurainx.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.*
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.MoreBooksView

interface MoreBooksPresenter: BasedPresenter, SmartBookDelegate{
    fun initView(view: MoreBooksView)

    fun onUiReady(owner: LifecycleOwner, listName: String)
    fun onTapAddToShelf(bookVO: BookVO)
    fun onTapAddToLibrary(bookVO: BookVO)
    fun onTapBookInfo(bookVO: BookVO)
    fun onTapBack()

}