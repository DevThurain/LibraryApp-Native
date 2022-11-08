package com.thurainx.libraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.CategoryAdapter
import com.thurainx.libraryapp.adapters.SmartBookAdapter
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.CategoryDelegate
import com.thurainx.libraryapp.delegate.SmartBookDelegate
import com.thurainx.libraryapp.delegate.SortingDelegate
import com.thurainx.libraryapp.utils.ListType
import com.thurainx.libraryapp.utils.SortType
import kotlinx.android.synthetic.main.sheet_dialog_grid.*
import kotlinx.android.synthetic.main.sheet_dialog_grid.view.*
import kotlinx.android.synthetic.main.sheet_dialog_sort.*
import kotlinx.android.synthetic.main.view_pod_book_list.view.*

class BookListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CoordinatorLayout(context, attrs) {


    var selectedListType : ListType = ListType.LIST
    var selectedSortType : SortType = SortType.BOOK_TITLE

    var categoryDelegate: CategoryDelegate? = null
    var smartBookDelegate: SmartBookDelegate? = null
    var sortingDelegate: SortingDelegate? = null

    lateinit var mCategoryAdapter: CategoryAdapter
    lateinit var mSmartBookAdapter: SmartBookAdapter

    fun setupViewPod(delegate1: CategoryDelegate, delegate2: SmartBookDelegate, delegate3: SortingDelegate){
        categoryDelegate = delegate1
        smartBookDelegate = delegate2
        sortingDelegate = delegate3
    }

    fun setupCategoryList(categoryList: List<CategoryVO>) {
        mCategoryAdapter = CategoryAdapter(categoryDelegate!!)
        rvCategory.adapter = mCategoryAdapter

        mCategoryAdapter.setNewData(categoryList)
    }

    fun updateCategory(categoryVO: CategoryVO){
        mCategoryAdapter.updateItem(categoryVO)
    }

    fun clearCategory(){
        mCategoryAdapter.clearItem()
    }

    fun setupBookList(bookList: List<BookVO>){
        mSmartBookAdapter = SmartBookAdapter(smartBookDelegate!!)
        rvSmartBookList.adapter = mSmartBookAdapter

        mSmartBookAdapter.setNewData(bookList)
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        setupListeners(context)
    }

    private fun setupListeners(context: Context) {
        btnSelectView.setOnClickListener {
            showGridDialog(context)
        }

        btnSelectSorting.setOnClickListener {
            showSortingDialog(context)
        }
    }

    private fun showGridDialog(context: Context){
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.sheet_dialog_grid)
        when(selectedListType){
            ListType.LIST -> {
                dialog.radioGroupListType.check(R.id.rbListView)
            }
            ListType.LARGE_GRID -> {
                dialog.radioGroupListType.check(R.id.rbLargeGridView)
            }
            ListType.SMALL_GRID -> {
                dialog.radioGroupListType.check(R.id.rbSmallGridView)
            }

        }
        dialog.radioGroupListType.setOnCheckedChangeListener( object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, selectedRadioId: Int) {
                when(selectedRadioId){
                    R.id.rbListView -> {
                        selectedListType = ListType.LIST
                    }
                    R.id.rbLargeGridView -> {
                        selectedListType = ListType.LARGE_GRID
                    }
                    R.id.rbSmallGridView -> {
                        selectedListType = ListType.SMALL_GRID
                    }
                }
                dialog.dismiss()
            }

        })

        dialog.show()
    }

    private fun showSortingDialog(context: Context){
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.sheet_dialog_sort)
        when(selectedSortType){
            SortType.BOOK_TITLE -> {
                dialog.radioGroupSortType.check(R.id.rbSortBookTitle)
                btnSelectSorting.text = "Sort By Book Title"
            }
            SortType.AUTHOR -> {
                dialog.radioGroupSortType.check(R.id.rbSortAuthor)
                btnSelectSorting.text = "Sort By Author"
            }
            SortType.DATE -> {
                dialog.radioGroupSortType.check(R.id.rbSortDate)
                btnSelectSorting.text = "Sort By Released Date"
            }

        }
        dialog.radioGroupSortType.setOnCheckedChangeListener( object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, selectedRadioId: Int) {
                when(selectedRadioId){
                    R.id.rbSortBookTitle -> {
                        selectedSortType = SortType.BOOK_TITLE
                        btnSelectSorting.text = "Sort By Book Title"
                    }
                    R.id.rbSortAuthor -> {
                        selectedSortType = SortType.AUTHOR
                        btnSelectSorting.text = "Sort By Author"
                    }
                    R.id.rbSortDate -> {
                        selectedSortType = SortType.DATE
                        btnSelectSorting.text = "Sort By Released Date"
                    }
                }
                sortingDelegate?.onTapSort(selectedSortType)
                dialog.dismiss()
            }

        })

        dialog.show()
    }



}