package com.thurainx.libraryapp.uitests.userflow1

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.activities.BasedActivity
import com.thurainx.libraryapp.uitests.utils.CAT_3_BOOK_1
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

        Thread.sleep(1000L)

        Espresso.onView(withText("Picture Books"))
            .perform(NestedScrollViewExtension())

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
            .viewHolderViewAtPosition(2, R.id.rvBooksFromBookList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<BookViewHolder>(0,
                    ViewActions.click()
                ))

        Thread.sleep(1000L)

        // app bar
        displayAppBar()

        // book detail
        displayBookDetail()

        // rating & reviews
        displayRatingAndReviews()

        // author & policy
        displayAuthorAndPolicy()

    }

    private fun displayAppBar(){
        Espresso.onView(withId(R.id.btnMovieDetailBack))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.ivBookDetailSearch))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.ivBookDetailAddToLibrary))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.ivBookDetailMore))
            .check(matches(isDisplayed()))
    }

    private fun displayBookDetail(){
        Espresso.onView(withId(R.id.tvBookDetailTitle))
            .check(matches(withText(CAT_3_BOOK_1)))

        Espresso.onView(withId(R.id.tvBookDetailAuthor))
            .check(matches(withText("Bessel van der Kolk")))

        Espresso.onView(withId(R.id.tvBookDetailReleasedDate))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.llReviewAndRating))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.llBookType))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.llPageCount))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutBookDetailAboutBook))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvBookDetailDescription))
            .check(matches(withText("How trauma affects the body and mind, and innovative treatments for recovery.")))

    }

    private fun displayRatingAndReviews(){

        Espresso.onView(withId(R.id.layoutBookDetailRating))
            .perform(NestedScrollViewExtension())

        Espresso.onView(withId(R.id.layoutBookDetailRating))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvBookDetailRatingLarge))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.rbBookDetailRating))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvBookDetailReviewCountLarge))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutFiveStarRating))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutFourStarRating))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutThreeStarRating))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutTwoStarRating))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutOneStarRating))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.rvBookDetailComments))
            .check(matches(isDisplayed()))

    }

    private fun displayAuthorAndPolicy(){
        Espresso.onView(withId(R.id.layoutBookDetailVAT))
            .perform(NestedScrollViewExtension())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.layoutBookDetailAboutAuthor))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvBookDetailAboutAuthorDescription))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutBookDetailRefund))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutBookDetailVAT))
            .check(matches(isDisplayed()))


    }

}