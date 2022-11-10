package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.mvp.presenters.BookDetailPresenter
import com.thurainx.libraryapp.mvp.presenters.BookDetailPresenterImpl
import com.thurainx.libraryapp.mvp.views.BookDetailView
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.view_holder_book.view.*


class BookDetailActivity : AppCompatActivity(), BookDetailView {

    lateinit var mPresenter: BookDetailPresenter

    companion object{
        fun getIntent(context: Context,bookVO: BookVO) : Intent{
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(EXTRA_BOOK_OBJECT, Gson().toJson(bookVO))
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        setupPresenter()
        setupListeners()
        intent.getStringExtra(EXTRA_BOOK_OBJECT)?.let {
            val book = Gson().fromJson(it,BookVO::class.java)
            mPresenter.onUiReadyBookDetail(owner = this, bookVO = book)
        }
    }

    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[BookDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupListeners(){
        btnMovieDetailBack.setOnClickListener {
            mPresenter.onTapBack()
        }
    }


    override fun bindBookData(bookVO: BookVO) {
        tvBookDetailTitle.text = bookVO.title
        tvBookDetailAuthor.text = "${bookVO.author}"
        tvBookDetailDescription.text = bookVO.description

        Glide.with(this)
            .load(bookVO.bookImage)
            .into(ivBookDetailCover)
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()
    }
}