package com.thurainx.libraryapp.delegate

import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO

interface CategoryDelegate {
    fun onTapCategory(categoryVO: CategoryVO)
}