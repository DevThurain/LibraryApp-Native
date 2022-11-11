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
import okhttp3.internal.cacheGet

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
                    if(selectedCategory == it.bookListName){
                        categoryList.add(CategoryVO(listName = it.bookListName, true))
                    }else{
                        categoryList.add(CategoryVO(listName = it.bookListName))
                    }

                }
                mYourBooksView?.showCategoryList(categoryList.toSet().toList())

                if(selectedCategory.isNotEmpty()){
                    onTapCategory(CategoryVO(listName = selectedCategory,true))
                }else{
                    mYourBooksView?.showBookList(bookList)
                }

            }


    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        selectedCategory = categoryVO.listName
        mYourBooksView?.onTapCategory(categoryVO)

//        mLibraryModel.getAllRecentBookByCategory(selectedCategory)
//            ?.observe(lifecycleOwner) { bookList ->
//                mYourBooksView?.showBookList(bookList)
//            }

        val bookList = mLibraryModel.getAllRecentBookByCategoryOneTime(category = categoryVO.listName) ?: listOf()
        mYourBooksView?.showBookList(bookList)


    }

    override fun onTapClearCategory() {
        selectedCategory = ""
        mYourBooksView?.onTapClearCategory()
//        mLibraryModel.getRecentBookListFromDatabase()
//            ?.observe(lifecycleOwner) { bookList ->
//                mYourBooksView?.showBookList(bookList)
//            }

        val bookList = mLibraryModel.getRecentBookListFromDatabaseOneTime() ?: listOf()
        mYourBooksView?.showBookList(bookList)

    }

    override fun onTapBook(bookVO: BookVO) {
        mYourBooksView?.navigateToBookDetail(bookVO)
    }

    override fun onTapMore(bookVO: BookVO) {
        mYourBooksView?.showBookInfoDialog(bookVO)
    }

    override fun onAddToShelf(bookVO: BookVO) {
        mYourBooksView?.navigateToAddToShelf(bookVO)
    }

    override fun onRemoveFromLibrary(bookVO: BookVO) {
        if(selectedCategory.isNotEmpty()){
            if(mLibraryModel.getAllRecentBookByCategoryOneTime(selectedCategory)?.size == 1){
                onTapClearCategory()
            }
        }
        mLibraryModel.deleteRecentBookByName(bookName = bookVO.title)

    }

}