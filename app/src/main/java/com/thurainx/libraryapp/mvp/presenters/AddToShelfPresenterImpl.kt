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
import com.thurainx.libraryapp.mvp.views.AddToShelfView
import com.thurainx.libraryapp.mvp.views.BookDetailView
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.mvp.views.YourBooksView
import com.thurainx.libraryapp.utils.SortType
import okhttp3.internal.cacheGet

class AddToShelfPresenterImpl : ViewModel(), AddToShelfPresenter {
    // view
    private var mAddToShelfView: AddToShelfView? = null

    // model
    private var mLibraryModel = LibraryModelImpl

    // state
    private var allShelf : ArrayList<ShelfVO> = arrayListOf()
    private var selectedShelf: ArrayList<String> = arrayListOf()


    override fun initView(view: AddToShelfView) {
        mAddToShelfView = view
    }


    override fun onUiReady(owner: LifecycleOwner) {

        mLibraryModel.getAllShelves()?.observe(owner){ shelfList ->
            allShelf.clear()
            allShelf.addAll(shelfList)
            mAddToShelfView?.showShelfList(shelfList)
        }

    }

    override fun onCheckShelf(shelfVO: ShelfVO, isChecked: Boolean) {
        if(isChecked){
            if(!selectedShelf.contains(shelfVO.name)){
                selectedShelf.add(shelfVO.name)
            }
        }else{
            if(selectedShelf.contains(shelfVO.name)){
                selectedShelf.remove(shelfVO.name)
            }
        }
    }

    override fun onTapSave(bookVO: BookVO) {
            allShelf.forEach { shelf ->
                if(selectedShelf.contains(shelf.name)){
                    val bookList = shelf.books.toMutableList()
                    if(bookList.firstOrNull { it.title == bookVO.title } == null) {
                        bookList.add(bookVO)
                        shelf.books = bookList
                    }
                }
            }

            mLibraryModel.insertShelfListToDatabase(allShelf)
        mAddToShelfView?.completeAddToShelf()

    }

    override fun onTapBack() {
        mAddToShelfView?.navigateBack()
    }


}