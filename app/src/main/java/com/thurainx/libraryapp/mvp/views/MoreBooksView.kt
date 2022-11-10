package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO

interface MoreBooksView : BasedView {
    fun showBookLists(bookList: List<BookVO>)
    fun showBookDetailDialog(book: BookVO)

    fun navigateToBookDetail(book: BookVO)
    fun navigateToAddToShelf(book: BookVO)
    fun navigateBack()
}