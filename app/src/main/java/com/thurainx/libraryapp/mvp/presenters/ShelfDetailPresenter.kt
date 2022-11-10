package com.thurainx.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.*
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.ShelfDetailView
import com.thurainx.libraryapp.mvp.views.YourBooksView

interface ShelfDetailPresenter: BasedPresenter, CategoryDelegate, SmartBookDelegate{
    fun initView(view: ShelfDetailView)
    fun onUiReadyShelfDetail(owner: LifecycleOwner, id: Long)
    fun onTapBack()
    fun onTapShelfMore()
    fun onRenameShelf(updatedName: String)
    fun onDeleteShelf()
    fun onDeleteShelfBook(bookVO: BookVO)

}