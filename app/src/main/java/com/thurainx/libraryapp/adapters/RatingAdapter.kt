package com.thurainx.libraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.libraryapp.delegate.BookDelegate
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.BookVO
import com.thurainx.libraryapp.viewholders.RatingViewHolder


class RatingAdapter() : RecyclerView.Adapter<RatingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_rating, parent, false)

        return RatingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bind((position + 1).toString())
    }

    override fun getItemCount(): Int {
        return 5
    }


}