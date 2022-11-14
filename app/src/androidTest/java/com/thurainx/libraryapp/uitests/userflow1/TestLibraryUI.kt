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
import com.thurainx.libraryapp.viewholders.CategoryViewHolder
import org.junit.*
import org.junit.Test
import org.junit.runner.OrderWith
import org.junit.runners.MethodSorters


@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class TestLibraryUI {
    @get:Rule
    val rule = activityScenarioRule<BasedActivity>()

    @After
    fun cleanUp() {
        rule.scenario.close()
    }

    @Test
    fun tapOnLibrary_showThreeCategories() {
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())


        Espresso.onView(withId(R.id.rvCategory))
            .check(matches(hasDescendant(withText("Hardcover Fiction"))))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvCategory))
            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(2))
            .check(matches(hasDescendant(withText("Hardcover Nonfiction"))))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvCategory))
            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(3))
            .check(matches(hasDescendant(withText("Paperback Nonfiction"))))

    }

    @Test
    fun tapOnCategory_showCategorizedBooks() {
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        // first book
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(1, R.id.tvCategoryName)
        ).perform(click())

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText("THE BOYS FROM BILOXI"))))

        // second book
        Espresso.onView(withId(R.id.rvCategory))
            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(2))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(2, R.id.tvCategoryName)
        ).perform(click())

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING"))))

        // third book
        Espresso.onView(withId(R.id.rvCategory))
            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(3))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(3, R.id.tvCategoryName)
        ).perform(click())

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText("THE BODY KEEPS THE SCORE"))))
    }

//    @Test
//    fun t3_tapOnCategoryTwo_showCategoryTwoBooks() {
//        Espresso.onView(withId(R.id.navLibrary))
//            .perform(click())
//
//        Espresso.onView(withId(R.id.rvCategory))
//            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(2))
//
//        Espresso.onView(
//            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
//                .viewHolderViewAtPosition(2, R.id.tvCategoryName)
//        ).perform(click())
//
//        Espresso.onView(withId(R.id.rvSmartBookList))
//            .check(matches(hasDescendant(withText("FRIENDS, LOVERS, AND THE BIG TERRIBLE THING"))))
//    }
//
//    @Test
//    fun t4_tapOnCategoryThree_showCategoryThreeBooks() {
//        Espresso.onView(withId(R.id.navLibrary))
//            .perform(click())
//
//        Espresso.onView(withId(R.id.rvCategory))
//            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(3))
//
//        Espresso.onView(
//            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
//                .viewHolderViewAtPosition(3, R.id.tvCategoryName)
//        ).perform(click())
//
//        Espresso.onView(withId(R.id.rvSmartBookList))
//            .check(matches(hasDescendant(withText("THE BODY KEEPS THE SCORE"))))
//    }

}