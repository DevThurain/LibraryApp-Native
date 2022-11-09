package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.mvp.presenters.CreateShelfPresenter
import com.thurainx.libraryapp.mvp.presenters.CreateShelfPresenterImpl
import com.thurainx.libraryapp.mvp.presenters.YourShelfPresenter
import com.thurainx.libraryapp.mvp.presenters.YourShelfPresenterImpl
import com.thurainx.libraryapp.mvp.views.CreateShelfView
import kotlinx.android.synthetic.main.activity_create_shelf.*

class CreateShelfActivity : AppCompatActivity(), CreateShelfView {

    lateinit var mPresenter: CreateShelfPresenter

    companion object{
        fun getIntent(context: Context) : Intent {
            val intent = Intent(context, CreateShelfActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)
        setupPresenter()
        setupListeners()
        mPresenter.initView(this)
    }

    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[CreateShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupListeners(){
        ivCreateShelfClose.setOnClickListener {
            mPresenter.onTapBack()
        }

        ivCreateShelfDone.setOnClickListener {
            mPresenter.onTapComplete(edtShelfName.text.toString())
        }

        edtShelfName.setOnEditorActionListener { textView, actionId, keyEvent ->
        return@setOnEditorActionListener when(actionId){
            EditorInfo.IME_ACTION_DONE -> {
                mPresenter.onTapComplete(textView.text.toString())
                true
            }
            else -> false
        }

        }
    }

    override fun insertShelfComplete() {
        super.onBackPressed()
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }

}