package com.thurainx.libraryapp.fragments

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.activities.AddToShelfActivity
import com.thurainx.libraryapp.activities.BookDetailActivity
import com.thurainx.libraryapp.activities.MoreBooksActivity
import com.thurainx.libraryapp.activities.SearchBookActivity
import com.thurainx.libraryapp.adapters.BookListAdapter
import com.thurainx.libraryapp.adapters.RecentBookAdapter
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.presenters.HomePresenter
import com.thurainx.libraryapp.mvp.presenters.HomePresenterImpl
import com.thurainx.libraryapp.mvp.views.HomeView
import com.thurainx.libraryapp.views.viewpods.BookTypeViewPod
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.sheet_dialog_book_download.*
import kotlinx.android.synthetic.main.sheet_dialog_book_info.*
import kotlinx.android.synthetic.main.sheet_shelf_update.*


class HomeFragment : Fragment(), HomeView {

    lateinit var bookTypeViewPod: BookTypeViewPod

    lateinit var mBookListAdapter: BookListAdapter
    lateinit var mRecentBookAdapter: RecentBookAdapter

    lateinit var mHomePresenter: HomePresenterImpl

    lateinit var recentBookCarousel: Carousel
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
        setupRecentBookRecyclerView()
        setupListeners()

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

    private fun setupRecentBookRecyclerView(){
        mRecentBookAdapter = RecentBookAdapter(mHomePresenter)
        recentBookCarousel = Carousel(requireActivity() as AppCompatActivity, rvRecentBooks, mRecentBookAdapter)
        recentBookCarousel.setOrientation(CarouselView.HORIZONTAL, false)
        recentBookCarousel.scaleView(true)

    }

    private fun setupListeners(){
        vpHomeSearch.setOnClickListener {
            mHomePresenter.onTapSearch()
        }
    }


    private fun setupViewPods(view: View) {
        bookTypeViewPod = view.vpBookTypeHome as BookTypeViewPod
    }

    override fun showRecentBookList(bookList: List<BookVO>) {
        mRecentBookAdapter.setNewData(bookList)
    }

    override fun showBookLists(bookLists: List<BookListVO>) {
        mBookListAdapter.setNewData(bookLists)
    }

    override fun showBookDetailDialog(book: BookVO) {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.sheet_dialog_book_download)
        dialog.show()

        dialog.show()

        Glide.with(this)
            .load(book.bookImage)
            .into(dialog.ivBookDownloadCover)

        dialog.tvBookDownloadName.text = book.title

        dialog.layoutBookDownloadAddToLibrary.setOnClickListener {
            mHomePresenter.onTapAddToLibrary(book)
            dialog.dismiss()
        }

        dialog.layoutBookDownloadAddToShelf.setOnClickListener {
            mHomePresenter.onTapAddToShelf(book)
            dialog.dismiss()
        }

        dialog.layoutBookDownloadAboutThisBook.setOnClickListener {
            mHomePresenter.onTapBookInfo(book)
            dialog.dismiss()
        }
    }

    override fun navigateToSearch() {
        val intent = SearchBookActivity.getIntent(requireActivity())
        startActivity(intent)

    }

    override fun navigateToBookDetail(book : BookVO) {
        val intent = BookDetailActivity.getIntent(requireActivity(),book)
        startActivity(intent)
    }

    override fun navigateToAddToShelf(book: BookVO) {
        val intent = AddToShelfActivity.getIntent(requireActivity(),book)
        startActivity(intent)
    }

    override fun navigateToMoreBook(bookList: BookListVO) {
        val intent = MoreBooksActivity.getIntent(requireActivity(), listName = bookList.listName.toString(), encodedListName = bookList.listNameEncoded.toString())
        startActivity(intent)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(requireView(),message,Snackbar.LENGTH_SHORT).show()
    }

}