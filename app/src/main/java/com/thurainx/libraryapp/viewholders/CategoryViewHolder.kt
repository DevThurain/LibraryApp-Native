package com.thurainx.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.delegate.CategoryDelegate
import kotlinx.android.synthetic.main.view_holder_book.view.*
import kotlinx.android.synthetic.main.viewholder_category.view.*

class CategoryViewHolder(itemView: View, delegate: CategoryDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mCategoryVO: CategoryVO? = null

    init {
        itemView.setOnClickListener {
            mCategoryVO?.let {
                delegate.onTapCategory(it)
            }
        }
    }

    fun bind(categoryVO: CategoryVO){
        mCategoryVO = categoryVO
        itemView.tvCategoryName.text = categoryVO.listName

        if(mCategoryVO?.isSelected == true){
            itemView.flCategory.background = itemView.context.getDrawable(R.drawable.background_category_selected)
            itemView.tvCategoryName.setTextColor(itemView.context.getColor(R.color.white))
        }else{
            itemView.flCategory.background = itemView.context.getDrawable(R.drawable.background_category_unselected)
            itemView.tvCategoryName.setTextColor(itemView.context.getColor(R.color.black))
        }

    }
}