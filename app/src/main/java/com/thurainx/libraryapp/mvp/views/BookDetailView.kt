package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO

interface BookDetailView : BasedView {
    fun bindBookData(bookVO: BookVO)
    fun navigateBack()
    fun navigateToRatingDetail()

}