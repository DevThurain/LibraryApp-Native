package com.thurainx.libraryapp.delegate

import com.thurainx.libraryapp.data.vos.ShelfVO

interface AddToShelfDelegate {
    fun onCheckShelf(shelfVO: ShelfVO, isChecked: Boolean)
}