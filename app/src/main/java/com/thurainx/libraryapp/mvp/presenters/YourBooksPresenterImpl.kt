package com.thurainx.libraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.mvp.views.BookDetailView
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.YourBooksView
import com.thurainx.libraryapp.utils.SortType

class YourBooksPresenterImpl : ViewModel(), YourBooksPresenter {
    // view
    private var mYourBooksView: YourBooksView? = null

    // model
    private var mLibraryModel = LibraryModelImpl

    // state
    private var selectedCategory = ""

    // lifecycle owner
    lateinit var lifecycleOwner: LifecycleOwner

    override fun initView(view: YourBooksView) {
        mYourBooksView = view
    }


    override fun onUiReady(owner: LifecycleOwner) {
        lifecycleOwner = owner
        mLibraryModel.getRecentBookListFromDatabase()
            ?.observe(owner) { bookList ->
                val categoryList: ArrayList<CategoryVO> = arrayListOf()
                bookList.forEach {
                    categoryList.add(CategoryVO(listName = it.bookListName))
                }
                mYourBooksView?.showCategoryList(categoryList.toSet().toList())
                mYourBooksView?.showBookList(bookList)

            }
    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        selectedCategory = categoryVO.listName
        mYourBooksView?.onTapCategory(categoryVO)

        mLibraryModel.getAllRecentBookByCategory(selectedCategory)
            ?.observe(lifecycleOwner) { bookList ->

                mYourBooksView?.showBookList(bookList)
            }
    }

    override fun onTapClearCategory() {
        selectedCategory = ""
        mYourBooksView?.onTapClearCategory()
        mLibraryModel.getRecentBookListFromDatabase()
            ?.observe(lifecycleOwner) { bookList ->
                mYourBooksView?.showBookList(bookList)
            }
    }

    override fun onTapBook(bookVO: BookVO) {

    }

    override fun onTapMore(bookVO: BookVO) {

    }


}