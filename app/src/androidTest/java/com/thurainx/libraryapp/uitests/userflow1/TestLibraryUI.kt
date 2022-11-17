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
import com.thurainx.libraryapp.uitests.utils.CAT_1_BOOK_1
import com.thurainx.libraryapp.uitests.utils.CAT_2_BOOK_1
import com.thurainx.libraryapp.uitests.utils.CAT_3_BOOK_1
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
    fun t1_tapOnLibrary_showThreeCategories() {
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
    fun t2_tapOnCategory_showCategoryBooks() {
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Thread.sleep(1000L)

        // first book
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(1, R.id.tvCategoryName)
        ).perform(click())

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText(CAT_1_BOOK_1))))

        // first clear
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(0, R.id.btnCategoryClear)
        ).perform(click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText(CAT_1_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_2_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_3_BOOK_1))))

        // second book
        Espresso.onView(withId(R.id.rvCategory))
            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(2))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(2, R.id.tvCategoryName)
        ).perform(click())

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText(CAT_2_BOOK_1))))


        // second clear
        Espresso.onView(withId(R.id.rvCategory))
            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(0))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(0, R.id.btnCategoryClear)
        ).perform(click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText(CAT_1_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_2_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_3_BOOK_1))))

        // third book

        Espresso.onView(withId(R.id.rvCategory))
            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(3))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(3, R.id.tvCategoryName)
        ).perform(click())

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText(CAT_3_BOOK_1))))

        // third clear
        Espresso.onView(withId(R.id.rvCategory))
            .perform(RecyclerViewActions.scrollToPosition<CategoryViewHolder>(0))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvCategory)
                .viewHolderViewAtPosition(0, R.id.btnCategoryClear)
        ).perform(click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(hasDescendant(withText(CAT_1_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_2_BOOK_1))))
            .check(matches(hasDescendant(withText(CAT_3_BOOK_1))))

    }


    @Test
    fun t3_tapOnChangeLayout_showSelectedLayout() {
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Thread.sleep(1000L)

        // large grid
        Espresso.onView(withId(R.id.btnSelectView))
            .perform(click())

        Espresso.onView(withId(R.id.rbLargeGridView))
            .perform(click())

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(0, R.id.ivBookLargeGridCover)
        )
            .check(matches(isDisplayed()))

        // small grid
        Espresso.onView(withId(R.id.btnSelectView))
            .perform(click())

        Espresso.onView(withId(R.id.rbSmallGridView))
            .perform(click())

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(0, R.id.ivBookSmallGridCover)
        )
            .check(matches(isDisplayed()))

        // list view
        Espresso.onView(withId(R.id.btnSelectView))
            .perform(click())

        Espresso.onView(withId(R.id.rbListView))
            .perform(click())

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(0, R.id.ivBookListViewCover)
        )
            .check(matches(isDisplayed()))


    }

    @Test
    fun t3_tapOnSort_showSortedBooks() {
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Thread.sleep(1000L)

        // Sort By Title
        Espresso.onView(withId(R.id.btnSelectSorting))
            .perform(click())

        Espresso.onView(withId(R.id.rbSortBookTitle))
            .perform(click())
        Thread.sleep(1000L)


        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(0, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_1_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(1, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(2, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_3_BOOK_1)))

        // Sort By Author
        Espresso.onView(withId(R.id.btnSelectSorting))
            .perform(click())

        Espresso.onView(withId(R.id.rbSortAuthor))
            .perform(click())
        Thread.sleep(1000L)

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(0, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_3_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(1, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(2, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_1_BOOK_1)))

        // Sort By Recently Opened
        Espresso.onView(withId(R.id.btnSelectSorting))
            .perform(click())

        Espresso.onView(withId(R.id.rbSortDate))
            .perform(click())
        Thread.sleep(1000L)


        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(0, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_3_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(1, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvSmartBookList)
                .viewHolderViewAtPosition(2, R.id.tvBookListViewTitle)
        )
            .check(matches(withText(CAT_1_BOOK_1)))


    }




}