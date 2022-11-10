package com.thurainx.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.BookDelegate
import kotlinx.android.synthetic.main.view_holder_book.view.*

class BookViewHolder(itemView: View,delegate: BookDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mBookVO: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBookVO?.let {
                delegate.onTapBook(it)
            }
        }

        itemView.ivBookViewMore?.setOnClickListener {
            mBookVO?.let {
                delegate.onTapMore(it)
            }
        }
    }

    fun bind(bookVO: BookVO){
        mBookVO = bookVO

        Glide.with(itemView.context)
            .load(bookVO.bookImage)
            .into(itemView.ivBookCover)

        itemView.tvBookPrice.text = "\$ ${bookVO.price.toString()}"
        itemView.tvBookTitle.text = bookVO.title

    }
}