package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.ShelfVO

interface AddToShelfView : BasedView {
    fun showShelfList(shelfList: List<ShelfVO>)
    fun completeAddToShelf()
    fun navigateBack()
}