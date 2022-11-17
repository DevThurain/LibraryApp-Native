package com.thurainx.libraryapp.uitests.userflow2

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.activities.BasedActivity
import com.thurainx.libraryapp.uitests.utils.CAT_1_BOOK_1
import com.thurainx.libraryapp.uitests.utils.CAT_2_BOOK_1
import com.thurainx.libraryapp.uitests.utils.CAT_3_BOOK_1
import com.thurainx.libraryapp.uitests.utils.selectTabAtPosition
import com.thurainx.libraryapp.viewholders.CategoryViewHolder
import com.thurainx.libraryapp.viewholders.ShelfViewHolder
import kotlinx.android.synthetic.main.fragment_library.view.*
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class TestShelfDetailUI {
    @get:Rule
    val rule = activityScenarioRule<BasedActivity>()

    @After
    fun cleanUp(){
        rule.scenario.close()
    }

    @Test
    fun t1_tapOnShelf_showShelfDetailUI(){
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tvShelfDetailName))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvShelfDetailBookCount))
            .check(matches(isDisplayed()))
    }

    @Test
    fun t2_tapOnShelf_showThreeCategories() {

        // navigate to shelf detail
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        Thread.sleep(1000L)

        // check three categories
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
    fun t3_tapOnShelfCategory_showCategoryBooks() {

        // navigate to shelf detail
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
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
    fun t4_tapOnShelfChangeLayout_showSelectedLayout() {
        // navigate to shelf detail
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
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
    fun t3_tapOnShelfSort_showSortedBooks() {
        // navigate to shelf detail
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
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

    @Test
    fun t5_tapOnRemoveBook_removeBook(){
        // navigate to shelf detail
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        Thread.sleep(1000L)

        // remove book from shelf
        Espresso.onView(RecyclerViewMatcher(R.id.rvSmartBookList).viewHolderViewAtPosition(0,R.id.ivBookListViewMore))
            .perform(click())

        checkDialogContents()

        Espresso.onView(withId(R.id.layoutShelfBookRemove))
            .perform(click())

        Espresso.onView(withId(R.id.rvSmartBookList))
            .check(matches(not(hasDescendant(withText(CAT_3_BOOK_1)))))
    }

    @Test
    fun t6_tapOnRename_renameShelfName(){

        // navigate to shelf detail
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        Thread.sleep(1000L)

        // rename
        Espresso.onView(withId(R.id.ivShelfDetailMore))
            .perform(click())

        Espresso.onView(withId(R.id.layoutRenameShelf))
            .perform(click())

        Espresso.onView(withId(R.id.edtShelfDetailShelfName))
            .check(matches(isDisplayed()))
            .check(matches(withText(TEST_SHELF_NAME)))
            .perform(
                ViewActions.replaceText(TEST_UPDATED_SHELF_NAME),
                pressImeActionButton()
            )
            .check(matches(withText(TEST_UPDATED_SHELF_NAME)))

        Espresso.onView(withId(R.id.tvShelfDetailName))
            .check(matches(isDisplayed()))
        Thread.sleep(1000L)
    }

    @Test
    fun t7_tapOnDelete_deleteShelf(){
        // navigate to shelf detail
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0, click()))
        Thread.sleep(1000L)

        // delete
        Espresso.onView(withId(R.id.ivShelfDetailMore))
            .perform(click())

        Espresso.onView(withId(R.id.layoutDeleteShelf))
            .perform(click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.layoutEmptyShelfList))
            .check(matches(isDisplayed()))
    }

    private fun checkDialogContents(){
        Espresso.onView(withId(R.id.ivShelfBookCover))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.tvShelfBookName))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutBookDownload))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutShelfBookRemove))
            .check(matches(isDisplayed()))
    }



    companion object{
        val TEST_SHELF_NAME = "Fun Books"
        val TEST_UPDATED_SHELF_NAME = "Fun Books Updated"
    }
}