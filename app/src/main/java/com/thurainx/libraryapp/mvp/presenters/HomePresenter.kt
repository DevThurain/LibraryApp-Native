package com.thurainx.libraryapp.mvp.presenters

import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.delegate.BookListDelegate
import com.thurainx.libraryapp.mvp.views.HomeView

interface HomePresenter: BasedPresenter, BookDelegate, BookListDelegate{
    fun initView(view: HomeView)

}