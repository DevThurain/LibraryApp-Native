package com.thurainx.libraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.CategoryDelegate
import com.thurainx.libraryapp.mvp.presenters.YourBooksPresenter
import com.thurainx.libraryapp.mvp.presenters.YourBooksPresenterImpl
import com.thurainx.libraryapp.mvp.views.YourBooksView
import com.thurainx.libraryapp.views.viewpods.BookListViewPod
import kotlinx.android.synthetic.main.fragment_your_books.view.*

class YourBooksFragment : Fragment(), YourBooksView {

    lateinit var vpYourBooks: BookListViewPod

    lateinit var mPresenter: YourBooksPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_books, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupBookListViewPod()

        mPresenter.onUiReady(this)
    }

    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[YourBooksPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupBookListViewPod(){
        vpYourBooks = view?.vpYourBooks as BookListViewPod
        vpYourBooks.setupViewPod(mPresenter,mPresenter, mPresenter)
    }

    override fun showCategoryList(categoryList: List<CategoryVO>) {
        vpYourBooks.setupCategoryList(categoryList)
    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        vpYourBooks.updateCategory(categoryVO)
    }

    override fun onTapClearCategory() {
        vpYourBooks.clearCategory()
    }

    override fun showBookList(bookList: List<BookVO>) {
        vpYourBooks.setupBookList(bookList)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(requireView(),message,Snackbar.LENGTH_SHORT).show()
    }

//    override fun onTapCategory(categoryVO: CategoryVO) {
//        vpYourBooks.updateData(categoryVO)
//    }
//
//    override fun onTapClearCategory() {
//        vpYourBooks.clearData()
//    }
}