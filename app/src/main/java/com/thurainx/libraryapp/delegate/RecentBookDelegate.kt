package com.thurainx.libraryapp.delegate

import com.thurainx.libraryapp.data.vos.BookVO

interface RecentBookDelegate {
    fun onTapRecentBook(bookVO: BookVO)
}