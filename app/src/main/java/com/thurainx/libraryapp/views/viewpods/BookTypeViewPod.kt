package com.thurainx.libraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_pod_book_type.view.*

class BookTypeViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    private val tabList = listOf("Ebooks", "Audiobooks")



    override fun onFinishInflate() {
        super.onFinishInflate()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        tabLayoutBookType?.let { tabLayout ->
            tabList.forEach {
                tabLayout.newTab().apply {
                    this.text = it
                    tabLayout.addTab(this)
                }
            }
        }

    }


}