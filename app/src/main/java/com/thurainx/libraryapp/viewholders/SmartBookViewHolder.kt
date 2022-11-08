package com.thurainx.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.SmartBookDelegate
import kotlinx.android.synthetic.main.layout_book_listview.view.*
import kotlinx.android.synthetic.main.view_holder_book.view.*

class SmartBookViewHolder(itemView: View, delegate: SmartBookDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mBookVO: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBookVO?.let {
                delegate.onTapBook(it)
            }
        }
    }

    fun bind(bookVO: BookVO){
        mBookVO = bookVO

        Glide.with(itemView.context)
            .load(bookVO.bookImage)
            .into(itemView.ivBookListViewCover)

        itemView.tvBookListViewTitle.text = bookVO.title
        itemView.tvBookListViewAuthor.text = bookVO.author
    }
}