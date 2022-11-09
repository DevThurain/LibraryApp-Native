package com.thurainx.libraryapp.mvp.views

import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO

interface YourBooksView : BasedView {
    fun showCategoryList(categoryList: List<CategoryVO>)
    fun onTapCategory(categoryVO: CategoryVO)
    fun onTapClearCategory()

    fun showBookList(bookList: List<BookVO>)

    fun showBookInfoDialog(bookVO: BookVO)
    fun navigateToAddToShelf(bookName: String)



}