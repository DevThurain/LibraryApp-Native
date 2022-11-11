package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.jakewharton.rxbinding4.widget.textChanges
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.SmartBookAdapter
import com.thurainx.libraryapp.adapters.VIEW_TYPE_LIST
import com.thurainx.libraryapp.data.models.LibraryModelImpl
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.presenters.SearchBookPresenter
import com.thurainx.libraryapp.mvp.presenters.SearchBookPresenterImpl
import com.thurainx.libraryapp.mvp.views.SearchBookView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search_book.*
import kotlinx.android.synthetic.main.sheet_dialog_book_download.*
import java.util.concurrent.TimeUnit

class SearchBookActivity : AppCompatActivity(), SearchBookView{

    lateinit var mPresenter: SearchBookPresenter
    lateinit var smartBookAdapter: SmartBookAdapter
    val mLibraryModel = LibraryModelImpl

    companion object{
        fun getIntent(context: Context) : Intent {
            val intent = Intent(context, SearchBookActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        setupPresenter()
        setupRecyclerView()
        setupListeners()
        setupEditText()
    }

    private fun setupPresenter() {
        mPresenter = ViewModelProvider(this)[SearchBookPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupRecyclerView(){
        smartBookAdapter = SmartBookAdapter(mPresenter, VIEW_TYPE_LIST)
        rvSearchBook.adapter = smartBookAdapter
    }

    private fun setupListeners(){
        ivSearchBack.setOnClickListener {
            mPresenter.onTapBack()
        }
    }

    private fun setupEditText(){
        edtBookSearch.textChanges()
            .debounce(1000L, TimeUnit.MILLISECONDS)
            .flatMap { mLibraryModel.searchBook(it.toString())}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                smartBookAdapter.setNewData(it)

            },{
                Snackbar.make(window.decorView,it.localizedMessage ?: "Unknown Search Error",Snackbar.LENGTH_SHORT).show()
            })
    }


    override fun showBookDetailDialog(book: BookVO) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.sheet_dialog_book_download)
        dialog.show()

        dialog.show()

        Glide.with(this)
            .load(book.bookImage)
            .into(dialog.ivBookDownloadCover)

        dialog.tvBookDownloadName.text = book.title

        dialog.layoutBookDownloadAddToLibrary.setOnClickListener {
            mPresenter.onTapAddToLibrary(book)
            dialog.dismiss()
        }

        dialog.layoutBookDownloadAddToShelf.setOnClickListener {
            mPresenter.onTapAddToShelf(book)
            dialog.dismiss()
        }

        dialog.layoutBookDownloadAboutThisBook.setOnClickListener {
            mPresenter.onTapBookInfo(book)
            dialog.dismiss()
        }
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun navigateToBookDetail(book : BookVO) {
        val intent = BookDetailActivity.getIntent(this,book)
        startActivity(intent)
    }

    override fun navigateToAddToShelf(book: BookVO) {
        val intent = AddToShelfActivity.getIntent(this,book)
        startActivity(intent)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()

    }
}