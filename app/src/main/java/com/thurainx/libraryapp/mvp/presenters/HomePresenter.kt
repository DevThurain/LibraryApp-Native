package com.thurainx.libraryapp.mvp.presenters

import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.delegate.BookListDelegate
import com.thurainx.libraryapp.delegate.RecentBookDelegate
import com.thurainx.libraryapp.delegate.SearchDelegate
import com.thurainx.libraryapp.mvp.views.HomeView

interface HomePresenter: BasedPresenter, BookDelegate, BookListDelegate, RecentBookDelegate{
    fun initView(view: HomeView)
    fun onTapSearch()
    fun onTapAddToShelf(bookVO: BookVO)
    fun onTapAddToLibrary(bookVO: BookVO)
    fun onTapBookInfo(bookVO: BookVO)

}