package com.thurainx.libraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RadioGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.*
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.CategoryDelegate
import com.thurainx.libraryapp.delegate.SmartBookDelegate
import com.thurainx.libraryapp.utils.ListType
import com.thurainx.libraryapp.utils.SortType
import com.thurainx.libraryapp.views.components.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.sheet_dialog_grid.*
import kotlinx.android.synthetic.main.sheet_dialog_sort.*
import kotlinx.android.synthetic.main.view_pod_book_list.view.*

class BookListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CoordinatorLayout(context, attrs) {


    var selectedListType : ListType = ListType.LIST
    var selectedSortType : SortType = SortType.BOOK_TITLE
    private val selectedBookList: MutableList<BookVO> = mutableListOf()

    var categoryDelegate: CategoryDelegate? = null
    var smartBookDelegate: SmartBookDelegate? = null

    lateinit var mCategoryAdapter: CategoryAdapter
    lateinit var mSmartBookAdapter: SmartBookAdapter

    fun setupViewPod(delegate1: CategoryDelegate, delegate2: SmartBookDelegate){
        categoryDelegate = delegate1
        smartBookDelegate = delegate2
    }

    fun setupCategoryList(categoryList: List<CategoryVO>) {
        if(categoryList.isEmpty()){
            rvCategory.visibility = View.INVISIBLE
            layoutFilters.visibility = View.INVISIBLE
            layoutEmptyCategoryList.visibility = View.VISIBLE
        }else{
            rvCategory.visibility = View.VISIBLE
            layoutFilters.visibility = View.VISIBLE
            layoutEmptyCategoryList.visibility = View.GONE
        }
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
        selectedBookList.clear()
        selectedBookList.addAll(bookList)

        showSortedList()
    }

    private fun showSortedList(){
        doSorting()

        when(selectedListType){
            ListType.LIST -> selectListView(selectedBookList)
            ListType.LARGE_GRID -> selectLargeGridView(selectedBookList)
            ListType.SMALL_GRID -> selectSmallGridView(selectedBookList)
        }
    }

    private fun selectListView(bookList: List<BookVO>){
        mSmartBookAdapter = SmartBookAdapter(smartBookDelegate!!, VIEW_TYPE_LIST)
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvSmartBookList.layoutManager = layoutManager
        rvSmartBookList.adapter = mSmartBookAdapter

        mSmartBookAdapter.setNewData(bookList)
    }

    private fun selectSmallGridView(bookList: List<BookVO>){
        mSmartBookAdapter = SmartBookAdapter(smartBookDelegate!!, VIEW_TYPE_SMALL_GRID)
        rvSmartBookList.adapter = mSmartBookAdapter
        val gridSpacing = GridSpacingItemDecoration(2, 0, true)
        val layoutManager = GridLayoutManager(context,2)
        rvSmartBookList.layoutManager = layoutManager
        rvSmartBookList.addItemDecoration(gridSpacing)

        mSmartBookAdapter.setNewData(bookList)

    }

    private fun selectLargeGridView(bookList: List<BookVO>){
        mSmartBookAdapter = SmartBookAdapter(smartBookDelegate!!, VIEW_TYPE_LARGE_GRID)
        rvSmartBookList.adapter = mSmartBookAdapter
        val gridSpacing = GridSpacingItemDecoration(3, 0, true)
        val layoutManager = GridLayoutManager(context,3,)
        rvSmartBookList.layoutManager = layoutManager
        rvSmartBookList.addItemDecoration(gridSpacing)

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
                when(selectedListType){
                    ListType.LIST -> selectListView(selectedBookList)
                    ListType.LARGE_GRID -> selectLargeGridView(selectedBookList)
                    ListType.SMALL_GRID -> selectSmallGridView(selectedBookList)
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
                btnSelectSorting.text = "Sort By Recently Opened"
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
                        btnSelectSorting.text = "Sort By Recently Opened"
                    }
                }
                showSortedList()
                dialog.dismiss()
            }

        })

        dialog.show()
    }



    private fun doSorting(){
        when(selectedSortType){
            SortType.BOOK_TITLE -> selectedBookList.sortBy { it.title.lowercase() }
            SortType.AUTHOR -> selectedBookList.sortBy { it.author?.lowercase() }
            SortType.DATE -> selectedBookList.sortByDescending { it.dateMillis }
        }
    }

}