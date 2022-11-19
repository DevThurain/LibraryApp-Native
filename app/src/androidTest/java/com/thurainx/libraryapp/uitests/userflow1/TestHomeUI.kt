package com.thurainx.libraryapp.uitests.userflow1

import android.widget.ImageView
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
import org.junit.runner.RunWith
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.uitests.utils.*
import com.thurainx.libraryapp.viewholders.BookViewHolder
import org.junit.*
import org.junit.Test
import org.junit.runner.OrderWith
import org.junit.runners.MethodSorters


@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class TestHomeUI {
    @get:Rule
    val rule = activityScenarioRule<BasedActivity>()

    @After
    fun cleanUp() {
        rule.scenario.close()
    }

    @Test
    fun t1_launch_emptyCarouselView() {
        Espresso.onView(withId(R.id.layoutEmptyRecentList))
            .check(matches(isDisplayed()))
    }

    @Test
    fun t2_launch_showThreeListThreeBooks() {

        Thread.sleep(2000L)

        // scroll to Hardcover Nonfiction
        Espresso.onView(withText("Hardcover Nonfiction"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(0, R.id.tvBookListTitle))
            .check(matches(withText("Hardcover Fiction")))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(0, R.id.rvBooksFromBookList))
            .check(matches(hasDescendant(withText(CAT_1_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_1_BOOK_2))))
            .check(matches(hasDescendant(withText(CAT_1_BOOK_3))))

        Thread.sleep(1000L)

        // scroll to Paperback Nonfiction
        Espresso.onView(withText("Paperback Nonfiction"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(1, R.id.tvBookListTitle))
            .check(matches(withText("Hardcover Nonfiction")))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(1, R.id.rvBooksFromBookList))
            .check(matches(hasDescendant(withText(CAT_2_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_2_BOOK_2))))
            .check(matches(hasDescendant(withText(CAT_2_BOOK_3))))

        Thread.sleep(1000L)

        // scroll to Picture Books
        Espresso.onView(withText("Picture Books"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(2, R.id.tvBookListTitle))
            .check(matches(withText("Paperback Nonfiction")))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(2, R.id.rvBooksFromBookList))
            .check(matches(hasDescendant(withText(CAT_3_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_3_BOOK_2))))
            .check(matches(hasDescendant(withText(CAT_3_BOOK_3))))

        Thread.sleep(1000L)

    }

    @Test
    fun t3_onTapBook_navigateToBookDetailAndAddedToCarousal() {
        Thread.sleep(2000L)

        // first book
        Espresso.onView(withText("Hardcover Nonfiction"))
            .perform(NestedScrollViewExtension())


        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(0, R.id.rvBooksFromBookList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookViewHolder>(0, click()))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.btnMovieDetailBack))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.btnMovieDetailBack))
            .perform(click())

        Thread.sleep(1000L)

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvRecentBooks)
            .viewHolderViewAtPosition(0,R.id.tvRecentBookName)
        ).check(matches(withText(CAT_1_BOOK_1)))

        // second book
        Espresso.onView(withText("Paperback Nonfiction"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(1, R.id.rvBooksFromBookList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookViewHolder>(0, click()))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.btnMovieDetailBack))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.btnMovieDetailBack))
            .perform(click())

        Thread.sleep(1000L)

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvRecentBooks)
            .viewHolderViewAtPosition(0,R.id.tvRecentBookName)
        ).check(matches(withText(CAT_2_BOOK_1)))

        //third book
        Espresso.onView(withText("Picture Books"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(2, R.id.rvBooksFromBookList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BookViewHolder>(0, click()))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.btnMovieDetailBack))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.btnMovieDetailBack))
            .perform(click())

        Thread.sleep(1000L)

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvRecentBooks)
            .viewHolderViewAtPosition(0,R.id.tvRecentBookName)
        ).check(matches(withText(CAT_3_BOOK_1)))




    }





}