package com.thurainx.libraryapp.mvp.presenters

import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.*
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.YourBooksView

interface YourBooksPresenter: BasedPresenter, CategoryDelegate, SmartBookDelegate{
    fun initView(view: YourBooksView)
    fun onAddToShelf(bookVO: BookVO)
    fun onRemoveFromLibrary(bookVO: BookVO)
}