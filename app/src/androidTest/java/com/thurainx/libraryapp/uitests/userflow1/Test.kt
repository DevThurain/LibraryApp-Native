package com.thurainx.libraryapp.uitests.userflow1

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.thurainx.libraryapp.activities.BasedActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.uitests.utils.NestedScrollViewExtension
import com.thurainx.libraryapp.viewholders.BookListViewHolder
import com.thurainx.libraryapp.viewholders.BookViewHolder
import kotlinx.coroutines.delay


@RunWith(AndroidJUnit4ClassRunner::class)
class Test {
    @get:Rule
    val rule = activityScenarioRule<BasedActivity>()

    @After
    fun cleanUp() {
        rule.scenario.close()
    }

    @Test
    fun launch_emptyCarouselView() {
        Espresso.onView(withId(R.id.layoutEmptyRecentList))
            .check(matches(isDisplayed()))
    }

    @Test
    fun launch_showThreeListName() {

        Espresso.onView(withText("Hardcover Nonfiction"))
            .perform(NestedScrollViewExtension())

//        Espresso.onView(withId(R.id.rvBookList))
//            .check(matches(hasDescendant(withText("Hardcover Fiction"))))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(0, R.id.tvBookListTitle))
            .check(matches(withText("Hardcover Fiction")))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(0, R.id.rvBooksFromBookList))
            .check(matches(hasDescendant(withText("THE BOYS FROM BILOXI"))))
            .check(matches(hasDescendant(withText("GOING ROGUE"))))
            .check(matches(hasDescendant(withText("NO PLAN C"))))

        Thread.sleep(2000L)

//        Espresso.onView(withText("Paperback Nonfiction"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(withId(R.id.rvBookList))
//            .check(matches(hasDescendant(withText("Hardcover Nonfiction"))))
//
//
//        Thread.sleep(2000L)
//
//        Espresso.onView(withText("Picture Books"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(withId(R.id.rvBookList))
//            .check(matches(hasDescendant(withText("Paperback Nonfiction"))))
//
//
//        Thread.sleep(2000L)

//
//        Espresso.onView(withId(R.id.rvBookList))
//            .perform(RecyclerViewActions.scrollToPosition<BookListViewHolder>(1))
//            .check(matches(hasDescendant(withText("Hardcover Nonfiction"))))
//
//        Thread.sleep(1000L)
//
//        Espresso.onView(withId(R.id.rvBookList))
//            .perform(RecyclerViewActions.scrollToPosition<BookListViewHolder>(2))
//            .check(matches(hasDescendant(withText("Paperback Nonfiction"))))
    }
}