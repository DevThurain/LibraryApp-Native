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
import com.thurainx.libraryapp.uitests.utils.NestedScrollViewExtension
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
    fun launch_emptyCarouselView() {
        Espresso.onView(withId(R.id.layoutEmptyRecentList))
            .check(matches(isDisplayed()))
    }

    @Test
    fun launch_showThreeListThreeBooks() {

        Espresso.onView(withText("Hardcover Nonfiction"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(0, R.id.tvBookListTitle))
            .check(matches(withText("Hardcover Fiction")))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(0, R.id.rvBooksFromBookList))
            .check(matches(hasDescendant(withText("THE BOYS FROM BILOXI"))))
            .check(matches(hasDescendant(withText("GOING ROGUE"))))
            .check(matches(hasDescendant(withText("NO PLAN B"))))

        Thread.sleep(1000L)

        Espresso.onView(withText("Paperback Nonfiction"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(1, R.id.tvBookListTitle))
            .check(matches(withText("Hardcover Nonfiction")))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(1, R.id.rvBooksFromBookList))
            .check(matches(hasDescendant(withText("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING"))))
            .check(matches(hasDescendant(withText("SURRENDER"))))
            .check(matches(hasDescendant(withText("THE PHILOSOPHY OF MODERN SONG"))))

        Thread.sleep(1000L)

        Espresso.onView(withText("Picture Books"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(2, R.id.tvBookListTitle))
            .check(matches(withText("Paperback Nonfiction")))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(2, R.id.rvBooksFromBookList))
            .check(matches(hasDescendant(withText("THE BODY KEEPS THE SCORE"))))
            .check(matches(hasDescendant(withText("BRAIDING SWEETGRASS"))))
            .check(matches(hasDescendant(withText("ALL ABOUT LOVE"))))

        Thread.sleep(1000L)



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

    @Test
    fun onTapBook_navigateToBookDetailAndAddedToCarousal() {
        // first book
        Espresso.onView(withText("Hardcover Nonfiction"))
            .perform(NestedScrollViewExtension())

        Thread.sleep(1000L)


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
        ).check(matches(withText("THE BOYS FROM BILOXI")))

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
        ).check(matches(withText("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING")))

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
        ).check(matches(withText("THE BODY KEEPS THE SCORE")))




    }

//    @Test
//    fun t5_onTapSecondBook_navigateToBookDetailAndAddedToCarousal() {
//        Espresso.onView(withText("Paperback Nonfiction"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
//            .viewHolderViewAtPosition(1, R.id.rvBooksFromBookList))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<BookViewHolder>(0, click()))
//
//        Thread.sleep(1000L)
//
//        Espresso.onView(withId(R.id.btnMovieDetailBack))
//            .check(matches(isDisplayed()))
//
//        Espresso.onView(withId(R.id.btnMovieDetailBack))
//            .perform(click())
//
//        Thread.sleep(1000L)
//
//        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvRecentBooks)
//            .viewHolderViewAtPosition(0,R.id.tvRecentBookName)
//        ).check(matches(withText("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING")))
//
//    }
//
//    @Test
//    fun t6_onTapThirdBook_navigateToBookDetailAndAddedToCarousal() {
//        Espresso.onView(withText("Picture Books"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
//            .viewHolderViewAtPosition(2, R.id.rvBooksFromBookList))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<BookViewHolder>(0, click()))
//
//        Thread.sleep(1000L)
//
//        Espresso.onView(withId(R.id.btnMovieDetailBack))
//            .check(matches(isDisplayed()))
//
//        Espresso.onView(withId(R.id.btnMovieDetailBack))
//            .perform(click())
//
//        Thread.sleep(1000L)
//
//        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvRecentBooks)
//            .viewHolderViewAtPosition(0,R.id.tvRecentBookName)
//        ).check(matches(withText("THE BODY KEEPS THE SCORE")))
//
//    }





}