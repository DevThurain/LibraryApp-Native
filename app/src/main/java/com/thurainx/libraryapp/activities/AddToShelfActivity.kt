package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.AddToShelfAdapter
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.presenters.AddToShelfPresenter
import com.thurainx.libraryapp.mvp.presenters.AddToShelfPresenterImpl
import com.thurainx.libraryapp.mvp.presenters.CreateShelfPresenter
import com.thurainx.libraryapp.mvp.views.AddToShelfView
import kotlinx.android.synthetic.main.activity_add_to_shelf.*


class AddToShelfActivity : AppCompatActivity(), AddToShelfView {

    lateinit var mAddToShelfAdapter: AddToShelfAdapter

    lateinit var mPresenter: AddToShelfPresenter

    companion object{
        fun getIntent(context: Context,bookName: String) : Intent {
            val intent = Intent(context, AddToShelfActivity::class.java)
            intent.putExtra(EXTRA_BOOK_NAME, bookName)
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
            intent.getStringExtra(EXTRA_BOOK_NAME)?.let { bookName ->
                mPresenter.onTapSave(bookName)
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