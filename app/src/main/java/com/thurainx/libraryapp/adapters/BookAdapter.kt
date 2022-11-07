package com.thurainx.libraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.viewholders.BookViewHolder


class BookAdapter(val delegate: BookDelegate) : RecyclerView.Adapter<BookViewHolder>(){

    private var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book,parent,false)

        return BookViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        if(mBookList.isNotEmpty()){
            holder.bind(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        if(mBookList.size > 10){
            return 10
        }else{
            return mBookList.size
        }
    }

    fun setNewData(movieList: List<BookVO>){
        mBookList = movieList
        notifyDataSetChanged()
    }
}