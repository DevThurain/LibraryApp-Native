package com.thurainx.libraryapp.uitests.userflow1

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.activities.BasedActivity
import com.thurainx.libraryapp.uitests.utils.NestedScrollViewExtension
import com.thurainx.libraryapp.viewholders.BookViewHolder
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class TestBookDetailUI {
    @get:Rule
    val rule = activityScenarioRule<BasedActivity>()

    @After
    fun cleanUp() {
        rule.scenario.close()
    }

    @Test
    fun onTapBook_checkBookDetailUI(){
        Espresso.onView(ViewMatchers.withText("Hardcover Nonfiction"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(0, R.id.rvBooksFromBookList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<BookViewHolder>(0,
                    ViewActions.click()
                ))

        Thread.sleep(1000L)

        Espresso.onView(ViewMatchers.withId(R.id.btnMovieDetailBack))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }




}