package com.thurainx.libraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookListVO
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.BookListDelegate
import com.thurainx.libraryapp.viewholders.BookListViewHolder


class BookListAdapter(val bookListDelegate: BookListDelegate, val bookDelegate: BookDelegate) : RecyclerView.Adapter<BookListViewHolder>(){

    private var mBookLists: List<BookListVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book_list,parent,false)

        return BookListViewHolder(view, bookListDelegate, bookDelegate)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        if(mBookLists.isNotEmpty()){
            holder.bind(mBookLists[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookLists.size
    }

    fun setNewData(bookLists: List<BookListVO>){
        mBookLists = bookLists
        notifyDataSetChanged()
    }
}