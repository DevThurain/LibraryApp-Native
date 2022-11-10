package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.data.vos.ShelfVO

interface ShelfDetailView : BasedView {
    fun showCategoryList(categoryList: List<CategoryVO>)
    fun onTapCategory(categoryVO: CategoryVO)
    fun onTapClearCategory()

    fun showBookList(bookList: List<BookVO>)
    fun showShelfDetail(shelfVO: ShelfVO)

    fun showBookInfoDialogForShelfBooks(bookVO: BookVO)
    fun showShelfUpdateDialog(shelfVO: ShelfVO)
    fun navigateBack()



}