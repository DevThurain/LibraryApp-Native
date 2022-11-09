package com.thurainx.libraryapp.mvp.presenters

import com.thurainx.libraryapp.delegate.*
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.YourShelfView

interface YourShelfPresenter: BasedPresenter, ShelfDelegate{
    fun initView(view: YourShelfView)
    fun onTapCreateShelf()
}