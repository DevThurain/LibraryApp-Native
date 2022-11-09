package com.thurainx.libraryapp.mvp.presenters

import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.delegate.*
import com.thurainx.libraryapp.mvp.views.CreateShelfView
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.YourShelfView

interface CreateShelfPresenter: BasedPresenter{
    fun initView(view: CreateShelfView)
    fun onTapComplete(shelfName: String)
    fun onTapBack()
}