package com.thurainx.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.views.BookDetailView

interface BookDetailPresenter: BasedPresenter{
    fun initView(view: BookDetailView)
    fun onUiReadyBookDetail(owner: LifecycleOwner, bookVO: BookVO)
    fun onTapBack()

}