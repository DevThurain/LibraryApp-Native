package com.thurainx.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.adapters.BookAdapter
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.delegate.BookListDelegate
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.view_holder_book.view.*
import kotlinx.android.synthetic.main.view_holder_book_list.view.*

class BookListViewHolder(itemView: View,val bookListDelegate: BookListDelegate,val bookDelegate: BookDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mBookListVO: BookListVO? = null
    lateinit var mBookAdapter: BookAdapter

    init {
        itemView.layoutMoreBook.setOnClickListener {
            mBookListVO?.let {
                bookListDelegate.onTapMoreBooks(it)
            }
        }
    }

    fun bind(bookListVO: BookListVO){
        mBookListVO = bookListVO

        itemView.tvBookListTitle.text = bookListVO.listName.toString()

        setupRecyclerView(books = bookListVO.books ?: listOf())
    }

    private fun setupRecyclerView(books: List<BookVO>){
        mBookAdapter = BookAdapter(delegate = bookDelegate)
        itemView.rvBooksFromBookList.adapter = mBookAdapter

        mBookAdapter.setNewData(books)
    }
}