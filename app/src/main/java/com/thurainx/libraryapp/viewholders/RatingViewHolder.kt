package com.thurainx.libraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.delegate.CategoryDelegate
import kotlinx.android.synthetic.main.view_holder_book.view.*
import kotlinx.android.synthetic.main.view_holder_category.view.*
import kotlinx.android.synthetic.main.view_category_clear.view.*
import kotlinx.android.synthetic.main.view_holder_rating.view.*

class RatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(rating: String){
        itemView.tvRatingCount.text = rating
    }


}