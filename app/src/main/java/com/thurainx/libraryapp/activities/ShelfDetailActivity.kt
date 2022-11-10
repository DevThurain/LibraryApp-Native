package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.presenters.ShelfDetailPresenter
import com.thurainx.libraryapp.mvp.presenters.ShelfDetailPresenterImpl
import com.thurainx.libraryapp.mvp.views.ShelfDetailView
import com.thurainx.libraryapp.utils.KeyboardUtils
import com.thurainx.libraryapp.utils.KeyboardUtils.hideKeyboard
 import com.thurainx.libraryapp.views.viewpods.BookListViewPod
import kotlinx.android.synthetic.main.activity_create_shelf.*
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.fragment_your_books.view.*
import kotlinx.android.synthetic.main.sheet_dialog_book_info.*
import kotlinx.android.synthetic.main.sheet_shelf_update.*

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

        ivShelfDetailBack.setOnClickListener {
            mPresenter.onTapBack()
        }
        ivShelfDetailMore.setOnClickListener {
            mPresenter.onTapShelfMore()
        }

        edtShelfDetailShelfName.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener when(actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    mPresenter.onRenameShelf(textView.text.toString())
                    tvShelfDetailName.visibility = View.VISIBLE
                    textInputLayoutShelfDetailShelfName.visibility = View.INVISIBLE
                    KeyboardUtils.hideKeyboard(textView,this)
                    true
                }
                else -> false
            }

        }
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

    override fun showShelfUpdateDialog(shelfVO: ShelfVO) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.sheet_shelf_update)
        dialog.show()

        dialog.tvShelfUpdateName.text = shelfVO.name

        dialog.layoutRenameShelf.setOnClickListener {
            tvShelfDetailName.visibility = View.INVISIBLE
            textInputLayoutShelfDetailShelfName.visibility = View.VISIBLE
            edtShelfDetailShelfName.setText(shelfVO.name)
            dialog.dismiss()
        }

        dialog.layoutDeleteShelf.setOnClickListener {
            mPresenter.onDeleteShelf()
        }
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }

}