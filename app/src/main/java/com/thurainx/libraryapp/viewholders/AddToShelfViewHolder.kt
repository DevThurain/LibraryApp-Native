package com.thurainx.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.delegate.AddToShelfDelegate
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.delegate.ShelfDelegate
import kotlinx.android.synthetic.main.view_holder_add_to_shelf.view.*
import kotlinx.android.synthetic.main.view_holder_book.view.*
import kotlinx.android.synthetic.main.view_holder_shelf.view.*

class AddToShelfViewHolder(itemView: View, delegate: AddToShelfDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mShelfVO: ShelfVO? = null

    init {
        itemView.cbAddToShelf?.addOnCheckedStateChangedListener { checkBox, state ->
            mShelfVO?.let {
                delegate.onCheckShelf(shelfVO = it, isChecked = checkBox.isChecked)
            }
        }
    }

    fun bind(shelfVO: ShelfVO){
        mShelfVO = shelfVO

        if(shelfVO.books.isEmpty()){
            Glide.with(itemView.context)
                .load(R.drawable.dummy_cover)
                .into(itemView.ivAddToShelfCover)
        }else{
            Glide.with(itemView.context)
                .load(shelfVO.books.last().bookImage)
                .into(itemView.ivAddToShelfCover)
        }


        itemView.tvAddToShelfCount.text = "${shelfVO.books.size} books"
        itemView.tvAddToShelfName.text = shelfVO.name

    }
}