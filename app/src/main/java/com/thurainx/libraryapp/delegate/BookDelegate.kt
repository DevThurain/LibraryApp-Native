package com.thurainx.libraryapp.delegate

import com.thurainx.libraryapp.data.vos.BookVO

interface BookDelegate {
    fun onTapBook(bookVO: BookVO)
}