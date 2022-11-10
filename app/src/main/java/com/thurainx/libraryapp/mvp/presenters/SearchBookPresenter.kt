package com.thurainx.libraryapp.mvp.presenters

import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.*
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.SearchBookView
import io.reactivex.rxjava3.core.Observable

interface SearchBookPresenter: BasedPresenter, SmartBookDelegate{
    fun initView(view: SearchBookView)
    fun onTapBack()

    fun onTapAddToShelf(bookVO: BookVO)
    fun onTapAddToLibrary(bookVO: BookVO)
    fun onTapBookInfo(bookVO: BookVO)

}