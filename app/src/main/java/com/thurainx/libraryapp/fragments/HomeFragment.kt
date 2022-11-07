package com.thurainx.libraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.BookListAdapter
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.presenters.HomePresenter
import com.thurainx.libraryapp.mvp.presenters.HomePresenterImpl
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.views.viewpods.BookTypeViewPod
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(), HomeView {

    lateinit var bookTypeViewPod: BookTypeViewPod

    lateinit var mBookListAdapter: BookListAdapter

    lateinit var mHomePresenter: HomePresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()

        setupViewPods(view)
        setupBookListsRecyclerView()

        mHomePresenter.onUiReady(this)

    }

    private fun setupPresenter(){
        mHomePresenter = ViewModelProvider(requireActivity())[HomePresenterImpl::class.java]
        mHomePresenter.initView(this)
    }

    private fun setupBookListsRecyclerView(){
        mBookListAdapter = BookListAdapter(mHomePresenter,mHomePresenter)
        rvBookList.adapter = mBookListAdapter
    }


    private fun setupViewPods(view: View) {
        bookTypeViewPod = view.vpBookTypeHome as BookTypeViewPod
    }

    override fun showRecentBookList(bookList: List<BookVO>) {

    }

    override fun showBookLists(bookLists: List<BookListVO>) {
        mBookListAdapter.setNewData(bookLists)
    }

    override fun navigateToBookDetail() {

    }

    override fun navigateToMoreBook(bookList: BookListVO) {

    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(requireView(),message,Snackbar.LENGTH_SHORT).show()
    }

}