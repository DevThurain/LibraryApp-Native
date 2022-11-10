package com.thurainx.libraryapp.delegate

import com.thurainx.libraryapp.data.vos.BookVO

interface SmartBookDelegate {
    fun onTapBook(bookVO: BookVO)
    fun onTapMore(bookVO: BookVO)
//    fun onAddToShelf(bookVO: BookVO)
//    fun onRemoveFromLibrary(bookVO: BookVO)
}