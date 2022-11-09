package com.thurainx.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.delegate.ShelfDelegate
import kotlinx.android.synthetic.main.view_holder_book.view.*
import kotlinx.android.synthetic.main.view_holder_shelf.view.*

class ShelfViewHolder(itemView: View, delegate: ShelfDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mShelfVO: ShelfVO? = null

    init {
        itemView.setOnClickListener {
            mShelfVO?.let {
                delegate.onTapShelf(it)
            }
        }
    }

    fun bind(shelfVO: ShelfVO){
        mShelfVO = shelfVO

        if(shelfVO.books.isEmpty() == true){
            Glide.with(itemView.context)
                .load(R.drawable.dummy_cover)
                .into(itemView.ivShelfCover)
        }else{
            Glide.with(itemView.context)
                .load(shelfVO.books?.last()?.bookImage)
                .into(itemView.ivShelfCover)
        }


        itemView.tvShelfBookCount.text = "${shelfVO.books.size} books"
        itemView.tvShelfName.text = shelfVO.name

    }
}