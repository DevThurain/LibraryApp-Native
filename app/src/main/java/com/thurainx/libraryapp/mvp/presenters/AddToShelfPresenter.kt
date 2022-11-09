package com.thurainx.libraryapp.mvp.presenters

import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.*
import com.thurainx.libraryapp.mvp.views.AddToShelfView
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.YourShelfView

interface AddToShelfPresenter: BasedPresenter, AddToShelfDelegate{
    fun initView(view: AddToShelfView)
    fun onTapSave(bookName: String)
    fun onTapBack()
}