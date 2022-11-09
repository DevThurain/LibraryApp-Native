package com.thurainx.libraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.delegate.AddToShelfDelegate
import com.thurainx.libraryapp.delegate.ShelfDelegate
import com.thurainx.libraryapp.viewholders.AddToShelfViewHolder


class AddToShelfAdapter(val delegate: AddToShelfDelegate) : RecyclerView.Adapter<AddToShelfViewHolder>(){

    private var mShelfList: List<ShelfVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_add_to_shelf,parent,false)

        return AddToShelfViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: AddToShelfViewHolder, position: Int) {
        if(mShelfList.isNotEmpty()){
            holder.bind(mShelfList[position])
        }
    }

    override fun getItemCount(): Int {
        return mShelfList.size
    }

    fun setNewData(shelfList: List<ShelfVO>){
        mShelfList = shelfList
        notifyDataSetChanged()
    }
}