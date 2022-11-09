package com.thurainx.libraryapp.delegate

import com.thurainx.libraryapp.data.vos.ShelfVO

interface ShelfDelegate {
    fun onTapShelf(shelfVO: ShelfVO)
}