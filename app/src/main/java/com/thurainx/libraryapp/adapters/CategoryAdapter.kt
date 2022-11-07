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

    private var mCategoryList: ArrayList<CategoryVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category,parent,false)

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

    fun setNewData(categoryList: ArrayList<CategoryVO>){
        mCategoryList = categoryList
        notifyDataSetChanged()
    }

    fun updateItem(categoryVO: CategoryVO){
        val position = mCategoryList.indexOf(categoryVO)
        mCategoryList.removeAt(position)
        notifyItemRemoved(position)

        mCategoryList.forEach {
            it.isSelected = false
        }
        notifyItemRangeChanged(0,mCategoryList.size)

        categoryVO.isSelected = true
        mCategoryList.add(0, categoryVO)
        notifyItemInserted(0)
    }

    fun clearItem(){
        mCategoryList.forEach {
            it.isSelected = false
        }
        notifyItemRangeChanged(0,mCategoryList.size)
    }
}