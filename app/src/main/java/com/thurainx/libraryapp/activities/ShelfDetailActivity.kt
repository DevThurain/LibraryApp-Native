package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.presenters.ShelfDetailPresenter
import com.thurainx.libraryapp.mvp.presenters.ShelfDetailPresenterImpl
import com.thurainx.libraryapp.mvp.views.ShelfDetailView
import com.thurainx.libraryapp.views.viewpods.BookListViewPod
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.fragment_your_books.view.*

const val EXTRA_SHELF_ID = "EXTRA_SHELF_ID"
class ShelfDetailActivity : AppCompatActivity(), ShelfDetailView {

    private lateinit var mPresenter: ShelfDetailPresenter
    private lateinit var vpShelfDetail: BookListViewPod

    companion object{
        fun getIntent(context: Context, id: Long) : Intent {
            val intent = Intent(context, ShelfDetailActivity::class.java)
            intent.putExtra(EXTRA_SHELF_ID, id)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelf_detail)

        setupPresenter()
        setupViewPod()
        setupListeners()

        intent?.getLongExtra(EXTRA_SHELF_ID,0)?.let{ id ->
            mPresenter.onUiReadyShelfDetail(this, id)
        }
    }

    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[ShelfDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupListeners(){

    }

    private fun setupViewPod(){
        vpShelfDetail = vpShelfDetailBookList as BookListViewPod
        vpShelfDetail.setupViewPod(mPresenter,mPresenter)
    }


    override fun showCategoryList(categoryList: List<CategoryVO>) {
        vpShelfDetail.setupCategoryList(categoryList)
    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        vpShelfDetail.updateCategory(categoryVO)
    }

    override fun onTapClearCategory() {
        vpShelfDetail.clearCategory()
    }

    override fun showBookList(bookList: List<BookVO>) {
        vpShelfDetail.setupBookList(bookList)
    }

    override fun showShelfDetail(shelfVO: ShelfVO) {
        tvShelfDetailName.text = shelfVO.name
        tvShelfDetailBookCount.text = shelfVO.books.size.toString().plus(" books")
    }

    override fun showBookInfoDialogForShelfBooks(bookVO: BookVO) {

    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }
}