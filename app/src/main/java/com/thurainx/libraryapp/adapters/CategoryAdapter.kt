package com.thurainx.libraryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.CategoryDelegate
import com.thurainx.libraryapp.viewholders.CategoryViewHolder


class CategoryAdapter(val delegate: CategoryDelegate) : RecyclerView.Adapter<CategoryViewHolder>(){

    private var mCategoryList: ArrayList<CategoryVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        lateinit var itemView : View
        when(viewType){
            R.layout.view_category_clear ->{
                itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_category_clear,parent,false)
            }
            else -> {
                itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_category,parent,false)
            }
        }
        return CategoryViewHolder(itemView, delegate)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            if(mCategoryList.size > 1 && position != 0){
                holder.bind(mCategoryList[position])
            }

    }

    override fun getItemCount(): Int {
        return mCategoryList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> R.layout.view_category_clear
            else -> R.layout.view_holder_category
        }
    }

    fun setNewData(categoryList: ArrayList<CategoryVO>){
        categoryList.add(0,CategoryVO(""))
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
        notifyItemRangeChanged(1,mCategoryList.size)

        categoryVO.isSelected = true
        mCategoryList.add(1, categoryVO)
        notifyItemInserted(1)
    }

    fun clearItem(){
        mCategoryList.forEach {
            it.isSelected = false
        }
        notifyItemRangeChanged(1,mCategoryList.size)
    }
}