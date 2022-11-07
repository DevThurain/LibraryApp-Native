package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO

interface HomeView : BasedView {
    fun showRecentBookList(bookList: List<BookVO>)
    fun showBookLists(bookLists: List<BookListVO>)
    fun navigateToSearch()
    fun navigateToBookDetail()
    fun navigateToMoreBook(bookList: BookListVO)
}