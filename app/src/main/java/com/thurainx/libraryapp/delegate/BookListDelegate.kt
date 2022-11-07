package com.thurainx.libraryapp.delegate

import com.thurainx.libraryapp.data.vos.BookListVO

interface BookListDelegate {
    fun onTapMoreBooks(bookList: BookListVO)
}