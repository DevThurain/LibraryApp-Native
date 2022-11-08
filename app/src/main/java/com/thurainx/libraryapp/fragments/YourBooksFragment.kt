package com.thurainx.libraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.data.vos.CategoryVO
import com.thurainx.libraryapp.delegate.CategoryDelegate
import com.thurainx.libraryapp.views.viewpods.BookListViewPod
import kotlinx.android.synthetic.main.fragment_your_books.view.*

class YourBooksFragment : Fragment(), CategoryDelegate {

    lateinit var vpYourBooks: BookListViewPod

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_books, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vpYourBooks = view.vpYourBooks as BookListViewPod
        vpYourBooks.setupViewPod(this)
    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        vpYourBooks.updateData(categoryVO)
    }

    override fun onTapClearCategory() {
        vpYourBooks.clearData()
    }
}