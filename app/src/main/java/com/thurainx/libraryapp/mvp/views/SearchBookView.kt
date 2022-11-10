package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import io.reactivex.rxjava3.core.Observable

interface SearchBookView : BasedView {
    fun showBookDetailDialog(book: BookVO)

    fun navigateBack()
    fun navigateToBookDetail(book: BookVO)
    fun navigateToAddToShelf(book: BookVO)
}