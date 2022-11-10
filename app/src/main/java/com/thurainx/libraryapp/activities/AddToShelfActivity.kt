package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.AddToShelfAdapter
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.presenters.AddToShelfPresenter
import com.thurainx.libraryapp.mvp.presenters.AddToShelfPresenterImpl
import com.thurainx.libraryapp.mvp.presenters.CreateShelfPresenter
import com.thurainx.libraryapp.mvp.views.AddToShelfView
import kotlinx.android.synthetic.main.activity_add_to_shelf.*


const val EXTRA_BOOK_OBJECT = "EXTRA_BOOK_OBJECT"
class AddToShelfActivity : AppCompatActivity(), AddToShelfView {

    lateinit var mAddToShelfAdapter: AddToShelfAdapter

    lateinit var mPresenter: AddToShelfPresenter

    companion object{
        fun getIntent(context: Context,bookVO: BookVO) : Intent {
            val intent = Intent(context, AddToShelfActivity::class.java)
            intent.putExtra(EXTRA_BOOK_OBJECT, Gson().toJson(bookVO))
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelf)

        setupPresenter()
        setupRecyclerView()
        setupListeners()
        mPresenter.onUiReady(this)
    }

    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[AddToShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupRecyclerView(){
        mAddToShelfAdapter = AddToShelfAdapter(mPresenter)
        rvAddToShelf.adapter = mAddToShelfAdapter
    }

    private fun setupListeners(){
        ivAddToShelClose.setOnClickListener {
            mPresenter.onTapBack()
        }

        ivAddToShelSave.setOnClickListener {
            intent.getStringExtra(EXTRA_BOOK_OBJECT)?.let { bookString ->
                val book = Gson().fromJson(bookString,BookVO::class.java)
                mPresenter.onTapSave(book)
            }
        }
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        mAddToShelfAdapter.setNewData(shelfList)
    }

    override fun completeAddToShelf() {
        super.onBackPressed()
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()
    }
}