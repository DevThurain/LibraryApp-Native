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
    private val mutableBookList: MutableList<BookVO> = mutableListOf()
    private var selectedCategory = ""
    private var selectedSortType = SortType.BOOK_TITLE

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

                mutableBookList.clear()
                mutableBookList.addAll(bookList)

                doSorting()
                mYourBooksView?.showBookList(mutableBookList)

            }
    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        selectedCategory = categoryVO.listName
        mYourBooksView?.onTapCategory(categoryVO)

        mLibraryModel.getAllRecentBookByCategory(selectedCategory)
            ?.observe(lifecycleOwner) { bookList ->
                mutableBookList.clear()
                mutableBookList.addAll(bookList)


                doSorting()

                mYourBooksView?.showBookList(mutableBookList)
            }
    }

    override fun onTapClearCategory() {
        selectedCategory = ""
        mYourBooksView?.onTapClearCategory()
    }

    override fun onTapBook(bookVO: BookVO) {

    }

    override fun onTapMore(bookVO: BookVO) {

    }

    override fun onTapSort(sortType: SortType) {
        selectedSortType = sortType

        doSorting()
        mutableBookList.forEach { book ->
            Log.d("bookList",book.createdDate.toString())
        }
        mYourBooksView?.showBookList(mutableBookList)
    }

    private fun doSorting(){
        when(selectedSortType){
            SortType.BOOK_TITLE -> mutableBookList.sortBy { it.title.lowercase() }
            SortType.AUTHOR -> mutableBookList.sortBy { it.author?.lowercase() }
            SortType.DATE -> mutableBookList.sortBy { it.dateMillis }
        }
    }


}