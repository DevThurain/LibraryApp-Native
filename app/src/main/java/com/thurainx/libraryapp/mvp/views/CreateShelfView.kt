package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.ShelfVO

interface CreateShelfView : BasedView {
    fun insertShelfComplete()
    fun navigateBack()
}