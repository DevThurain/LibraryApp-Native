package com.thurainx.libraryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.delegate.SmartBookDelegate
import com.thurainx.libraryapp.viewholders.SmartBookViewHolder

const val VIEW_TYPE_LIST = "VIEW_TYPE_LIST"
const val VIEW_TYPE_LARGE_GRID = "VIEW_TYPE_LARGE_GRID"
const val VIEW_TYPE_SMALL_GRID = "VIEW_TYPE_SMALL_GRID"

class SmartBookAdapter(val delegate: SmartBookDelegate, private val selectedViewType: String) :
    RecyclerView.Adapter<SmartBookViewHolder>() {

    private var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartBookViewHolder {
        var view: View? = null
        when (selectedViewType) {
            VIEW_TYPE_LIST -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.layout_book_listview, parent, false)
            }
            VIEW_TYPE_LARGE_GRID -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.layout_book_large_gridview, parent, false)
            }
            VIEW_TYPE_SMALL_GRID -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.layout_book_small_gridview, parent, false)
            }
        }

        return SmartBookViewHolder(view!!, delegate, selectedViewType)
    }

    override fun onBindViewHolder(holder: SmartBookViewHolder, position: Int) {
        if (mBookList.isNotEmpty()) {
            holder.bind(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookList.size
    }

    fun setNewData(movieList: List<BookVO>) {
        mBookList = movieList
        notifyDataSetChanged()
    }
}