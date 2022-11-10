package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.SmartBookAdapter
import com.thurainx.libraryapp.adapters.VIEW_TYPE_LARGE_GRID
import com.thurainx.libraryapp.adapters.VIEW_TYPE_SMALL_GRID
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.presenters.AddToShelfPresenter
import com.thurainx.libraryapp.mvp.presenters.MoreBooksPresenter
import com.thurainx.libraryapp.mvp.presenters.MoreBooksPresenterImpl
import com.thurainx.libraryapp.mvp.views.MoreBooksView
import com.thurainx.libraryapp.views.components.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_more_books.*
import kotlinx.android.synthetic.main.sheet_dialog_book_download.*
import kotlinx.android.synthetic.main.view_pod_book_list.view.*

const val EXTRA_LIST_NAME = "EXTRA_LIST_NAME"
const val EXTRA_ENCODED_LIST_NAME = "EXTRA_ENCODED_LIST_NAME"

class MoreBooksActivity : AppCompatActivity(), MoreBooksView {

    lateinit var smartBookAdapter: SmartBookAdapter

    lateinit var mPresenter: MoreBooksPresenter

    companion object{
        fun getIntent(context: Context, listName: String, encodedListName: String) : Intent {
            val intent = Intent(context, MoreBooksActivity::class.java)
            intent.putExtra(EXTRA_LIST_NAME, listName)
            intent.putExtra(EXTRA_ENCODED_LIST_NAME, encodedListName)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_books)

        setUpPresenter()
        setUpRecyclerView()
        bindData()
        setUpListeners()
        intent?.getStringExtra(EXTRA_ENCODED_LIST_NAME)?.let {
            mPresenter.onUiReady(this, it)
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProvider(this)[MoreBooksPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpRecyclerView(){
        smartBookAdapter = SmartBookAdapter(mPresenter, selectedViewType = VIEW_TYPE_SMALL_GRID)
        rvMoreBooks.adapter = smartBookAdapter
        val gridSpacing = GridSpacingItemDecoration(2, 0, true)
        val layoutManager = GridLayoutManager(this,2)
        rvMoreBooks.layoutManager = layoutManager
        rvMoreBooks.addItemDecoration(gridSpacing)
    }

    private fun bindData(){
        tvMoreBooksTitle.text =  intent?.getStringExtra(EXTRA_LIST_NAME)
    }

    private fun setUpListeners(){
        ivMoreBooksBack.setOnClickListener {
            mPresenter.onTapBack()
        }
    }

    override fun showBookLists(bookList: List<BookVO>) {
        smartBookAdapter.setNewData(bookList)
    }

    override fun showBookDetailDialog(book: BookVO) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.sheet_dialog_book_download)
        dialog.show()

        dialog.show()

        Glide.with(this)
            .load(book.bookImage ?: R.drawable.dummy_cover)
            .into(dialog.ivBookDownloadCover)

        dialog.tvBookDownloadName.text = book.title

        dialog.layoutBookDownloadAddToLibrary.setOnClickListener {
//            mPresenter.onTapAddToLibrary(book)
            dialog.dismiss()
        }

        dialog.layoutBookDownloadAddToShelf.setOnClickListener {
//            mPresenter.onTapAddToShelf(book)
            dialog.dismiss()
        }

        dialog.layoutBookDownloadAboutThisBook.setOnClickListener {
//            mPresenter.onTapBookInfo(book)
            dialog.dismiss()
        }
    }

    override fun navigateToBookDetail(book: BookVO) {
//        val intent = BookDetailActivity.getIntent(this,book)
//        startActivity(intent)
    }

    override fun navigateToAddToShelf(book: BookVO) {
//        val intent = AddToShelfActivity.getIntent(this,book)
//        startActivity(intent)
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()
    }
}