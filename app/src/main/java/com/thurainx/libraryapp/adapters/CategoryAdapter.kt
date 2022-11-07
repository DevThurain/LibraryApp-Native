package com.thurainx.libraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.CategoryDelegate
import com.thurainx.libraryapp.viewholders.CategoryViewHolder


class CategoryAdapter(val delegate: CategoryDelegate) : RecyclerView.Adapter<CategoryViewHolder>(){

    private var mCategoryList: List<CategoryVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book,parent,false)

        return CategoryViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if(mCategoryList.isNotEmpty()){
            holder.bind(mCategoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return mCategoryList.size
    }

    fun setNewData(categoryList: List<CategoryVO>){
        mCategoryList = categoryList
        notifyDataSetChanged()
    }
}