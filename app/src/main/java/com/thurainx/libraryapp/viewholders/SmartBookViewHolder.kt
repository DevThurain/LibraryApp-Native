package com.thurainx.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.adapters.VIEW_TYPE_LARGE_GRID
import com.thurainx.libraryapp.adapters.VIEW_TYPE_LIST
import com.thurainx.libraryapp.adapters.VIEW_TYPE_SMALL_GRID
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.SmartBookDelegate
import kotlinx.android.synthetic.main.layout_book_large_gridview.view.*
import kotlinx.android.synthetic.main.layout_book_listview.view.*
import kotlinx.android.synthetic.main.layout_book_small_gridview.view.*

class SmartBookViewHolder(itemView: View, delegate: SmartBookDelegate, private val selectedViewType: String) : RecyclerView.ViewHolder(itemView) {
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
        when(selectedViewType){
            VIEW_TYPE_LIST -> bindListView(bookVO)
            VIEW_TYPE_LARGE_GRID -> bindLargeGridView(bookVO)
            VIEW_TYPE_SMALL_GRID -> bindSmallGridView(bookVO)
        }
    }

    private fun bindListView(bookVO: BookVO){
        Glide.with(itemView.context)
            .load(bookVO.bookImage)
            .into(itemView.ivBookListViewCover)

        itemView.tvBookListViewTitle.text = bookVO.title
        itemView.tvBookListViewAuthor.text = bookVO.author
    }

    private fun bindLargeGridView(bookVO: BookVO){
        Glide.with(itemView.context)
            .load(bookVO.bookImage)
            .into(itemView.ivBookLargeGridCover)

        itemView.tvBookLargeGridViewTitle.text = bookVO.title
        itemView.tvBookLargeGridViewAuthor.text = bookVO.author
    }

    private fun bindSmallGridView(bookVO: BookVO){
        Glide.with(itemView.context)
            .load(bookVO.bookImage)
            .into(itemView.ivBookSmallGridCover)

        itemView.tvBookSmallGridViewTitle.text = bookVO.title
        itemView.tvBookSmallGridViewAuthor.text = bookVO.author
    }

}