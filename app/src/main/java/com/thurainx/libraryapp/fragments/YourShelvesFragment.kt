package com.thurainx.libraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.activities.CreateShelfActivity
import com.thurainx.libraryapp.adapters.ShelfAdapter
import com.thurainx.libraryapp.data.vos.ShelfVO
import com.thurainx.libraryapp.mvp.presenters.YourShelfPresenter
import com.thurainx.libraryapp.mvp.presenters.YourShelfPresenterImpl
import com.thurainx.libraryapp.mvp.views.YourShelfView
import kotlinx.android.synthetic.main.fragment_your_shelves.*

class YourShelvesFragment : Fragment(), YourShelfView {

    lateinit var mShelfAdapter: ShelfAdapter

    lateinit var mPresenter: YourShelfPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_shelves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupShelfRecyclerView()
        setupListeners()
        mPresenter.onUiReady(this)
    }

    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[YourShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupShelfRecyclerView(){
        mShelfAdapter = ShelfAdapter(mPresenter)
        rvShelves.adapter = mShelfAdapter
    }

    private fun setupListeners(){
        btnCreateShelf.setOnClickListener {
            mPresenter.onTapCreateShelf()
        }
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        mShelfAdapter.setNewData(shelfList)
    }

    override fun navigateToCreateShelf() {
        val intent = CreateShelfActivity.getIntent(requireActivity())
        startActivity(intent)
    }

    override fun navigateToShelfDetail(shelfName: String) {


    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(requireView(),message, Snackbar.LENGTH_SHORT).show()
    }

}