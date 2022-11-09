package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.ShelfVO

interface YourShelfView : BasedView {
    fun showShelfList(shelfList: List<ShelfVO>)
    fun navigateToCreateShelf()
    fun navigateToShelfDetail(shelfName: String)
}