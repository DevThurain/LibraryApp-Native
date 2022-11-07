package com.thurainx.libraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.LibraryViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_library.view.*
import kotlinx.android.synthetic.main.view_pod_book_type.view.*


class LibraryFragment : Fragment() {
    // adapters
    lateinit var mLibraryViewPagerAdapter: LibraryViewPagerAdapter
    // states
    private val tabList = listOf("Your books", "Your shelves")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout(view)
        setupViewPager(view)
    }

    private fun setupTabLayout(view: View) {
        view.tabLayoutLibrary?.let { tabLayout ->
            tabList.forEach {
                tabLayout.newTab().apply {
                    this.text = it
                    tabLayout.addTab(this)
                }
            }
        }

        view.tabLayoutLibrary.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view.viewPagerLibrary.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun setupViewPager(view: View) {
        mLibraryViewPagerAdapter = LibraryViewPagerAdapter(requireActivity())
        view.viewPagerLibrary.adapter = mLibraryViewPagerAdapter

        view.viewPagerLibrary.registerOnPageChangeCallback( object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val tab = view.tabLayoutLibrary.getTabAt(position)
                tab?.select()
            }
        })
    }

}