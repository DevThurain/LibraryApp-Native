package com.thurainx.libraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.fragments.HomeFragment
import com.thurainx.libraryapp.fragments.LibraryFragment
import kotlinx.android.synthetic.main.activity_based.*

class BasedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_based)

        setupBottomNavigationView()

    }

    private fun setupBottomNavigationView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, HomeFragment())
            .commit()

        bottomNavigationView.setOnItemSelectedListener {
                menuItem ->

            when(menuItem.itemId){
                R.id.navHome -> {
                    switchFragment(HomeFragment())
                }
                R.id.navLibrary -> {
                    switchFragment(LibraryFragment())
                }
            }

            return@setOnItemSelectedListener true

        }
    }

    private fun switchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, fragment)
            .commit()
    }
}