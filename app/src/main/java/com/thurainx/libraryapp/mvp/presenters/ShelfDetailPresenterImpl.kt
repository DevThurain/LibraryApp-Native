package com.thurainx.libraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.views.BookDetailView
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.ShelfDetailView
import com.thurainx.libraryapp.mvp.views.YourBooksView
import com.thurainx.libraryapp.utils.SortType
import okhttp3.internal.cacheGet
import okhttp3.internal.filterList

class ShelfDetailPresenterImpl : ViewModel(), ShelfDetailPresenter {
    // view
    private var mShelfDetailView: ShelfDetailView? = null

    // model
    private var mLibraryModel = LibraryModelImpl

    // state
    private var selectedCategory = ""
    private var selectedShelf: ShelfVO? = null


    override fun initView(view: ShelfDetailView) {
        mShelfDetailView = view
    }

    override fun onUiReadyShelfDetail(owner: LifecycleOwner, id: Long) {
        mLibraryModel.getShelfById(id)?.observe(owner){
            it?.let { shelf ->
                selectedShelf = shelf
                mShelfDetailView?.showShelfDetail(shelf)

                val categoryList: ArrayList<CategoryVO> = arrayListOf()
                shelf.books.forEach {
                    if(selectedCategory == it.bookListName){
                        categoryList.add(CategoryVO(listName = it.bookListName, true))
                    }else{
                        categoryList.add(CategoryVO(listName = it.bookListName))
                    }

                }
                mShelfDetailView?.showCategoryList(categoryList.toSet().toList())
                if(selectedCategory.isNotEmpty()){
                    onTapCategory(CategoryVO(listName = selectedCategory,true))
                }else{
                    mShelfDetailView?.showBookList(shelf.books)
                }
            }
        }
    }

    override fun onTapShelfMore() {
        selectedShelf?.let {
            mShelfDetailView?.showShelfUpdateDialog(it)
        }
    }

    override fun onRenameShelf(updatedName: String) {
        val updatedShelf = selectedShelf?.copy(name = updatedName)
        if(updatedName.isNotEmpty()){
            updatedShelf?.let {
                mLibraryModel.insertShelfToDatabase(updatedShelf)
            }
        }else{
            mShelfDetailView?.showErrorMessage("Shelf name cannot be empty.")
        }

    }

    override fun onDeleteShelf() {
        selectedShelf?.let {
            mLibraryModel.deleteShelf(it.uniqueId)
            mShelfDetailView?.navigateBack()

        }
    }


    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        selectedCategory = categoryVO.listName
        mShelfDetailView?.onTapCategory(categoryVO)

        val bookList = selectedShelf?.books?.filter { it.bookListName == categoryVO.listName } ?: listOf()
        mShelfDetailView?.showBookList(bookList)


    }

    override fun onTapClearCategory() {
        selectedCategory = ""
        mShelfDetailView?.onTapClearCategory()

        val bookList = selectedShelf?.books ?: listOf()
        mShelfDetailView?.showBookList(bookList)

    }

    override fun onTapBook(bookVO: BookVO) {

    }

    override fun onTapMore(bookVO: BookVO) {
//        mShelfDetailView?.showBookInfoDialogForShelfBooks(bookVO)
    }

    override fun onAddToShelf(bookVO: BookVO) {
//        mYourBooksView?.navigateToAddToShelf(bookVO.title)
    }

    override fun onRemoveFromLibrary(bookVO: BookVO) {
//        if(selectedCategory.isNotEmpty()){
//            if(mLibraryModel.getAllRecentBookByCategoryOneTime(selectedCategory)?.size == 1){
//                onTapClearCategory()
//            }
//        }
//        mLibraryModel.deleteRecentBookByName(bookName = bookVO.title)

    }

}