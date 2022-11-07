package com.thurainx.libraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.thurainx.libraryapp.delegate.SearchDelegate

class SearchViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    var searchDelegate: SearchDelegate? = null

    fun setupDelegate(delegate: SearchDelegate){
        searchDelegate = delegate
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }
}