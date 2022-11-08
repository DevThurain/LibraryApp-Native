package com.thurainx.libraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.SmartBookDelegate
import com.thurainx.libraryapp.viewholders.SmartBookViewHolder


class SmartBookAdapter(val delegate: SmartBookDelegate) : RecyclerView.Adapter<SmartBookViewHolder>(){

    private var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_book_listview,parent,false)

        return SmartBookViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: SmartBookViewHolder, position: Int) {
        if(mBookList.isNotEmpty()){
            holder.bind(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookList.size
    }

    fun setNewData(movieList: List<BookVO>){
        mBookList = movieList
        notifyDataSetChanged()
    }
}