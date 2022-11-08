package com.thurainx.libraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.thurainx.libraryapp.adapters.CategoryAdapter
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.CategoryDelegate
import kotlinx.android.synthetic.main.view_pod_book_list.view.*

class BookListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    var categoryList = arrayListOf(
        CategoryVO(listName = "One"),
        CategoryVO(listName = "Two"),
        CategoryVO(listName = "Three"),
        CategoryVO(listName = "Four"),
        )

    var categoryDelegate: CategoryDelegate? = null

    lateinit var mCategoryAdapter: CategoryAdapter

    fun setupViewPod(delegate: CategoryDelegate){
        categoryDelegate = delegate
        setupRecyclerView(delegate)
    }

    private fun setupRecyclerView(delegate: CategoryDelegate) {
        mCategoryAdapter = CategoryAdapter(delegate)
        rvCategory.adapter = mCategoryAdapter

        mCategoryAdapter.setNewData(categoryList)
    }

    fun updateData(categoryVO: CategoryVO){
        mCategoryAdapter.updateItem(categoryVO)
    }

    fun clearData(){
        mCategoryAdapter.clearItem()
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
    }


}