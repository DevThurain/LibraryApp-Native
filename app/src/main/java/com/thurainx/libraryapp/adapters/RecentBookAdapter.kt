package com.thurainx.libraryapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.RecentBookDelegate
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.view_holder_recent_book.view.*


class RecentBookAdapter(val delegate: RecentBookDelegate) : CarouselAdapter() {

    private var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recent_book,parent,false)

        return RecentBookViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val recentBookViewHolder = holder as RecentBookViewHolder
        if(mBookList.isNotEmpty()){
            recentBookViewHolder.bind(mBookList[position])
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

    inner class RecentBookViewHolder(itemView: View, delegate: RecentBookDelegate) : CarouselViewHolder(itemView) {
        private var mBookVO: BookVO? = null

        init {
            itemView.setOnClickListener {
                mBookVO?.let {
                    delegate.onTapRecentBook(it)
                }
            }
        }

        fun bind(bookVO: BookVO){
            mBookVO = bookVO

            Glide.with(itemView.context)
                .load(bookVO.bookImage)
                .into(itemView.ivRecentBookCover)

            itemView.tvRecentBookName.text = bookVO.title
        }
    }
}