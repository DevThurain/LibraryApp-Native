package com.thurainx.libraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.CommentAdapter
import com.thurainx.libraryapp.adapters.RatingAdapter
import com.thurainx.libraryapp.data.vos.BookVO
import kotlinx.android.synthetic.main.activity_review_detail.*
import kotlinx.android.synthetic.main.view_holder_rating.*
import kotlinx.android.synthetic.main.view_holder_rating.ivReviewDetailBack

class ReviewDetailActivity : AppCompatActivity() {
    lateinit var ratingAdapter: RatingAdapter
    lateinit var commentAdapter: CommentAdapter

    companion object{
        fun getIntent(context: Context) : Intent {
            val intent = Intent(context, ReviewDetailActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)

        setupListeners()
        setupRatingRecyclerView()
        setupCommentRecyclerView()
    }

    private fun setupCommentRecyclerView() {
        commentAdapter = CommentAdapter()
        rvComments.adapter = commentAdapter
    }

    private fun setupRatingRecyclerView() {
        ratingAdapter = RatingAdapter()
        rvRating.adapter = ratingAdapter
    }

    private fun setupListeners() {
        ivReviewDetailBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}