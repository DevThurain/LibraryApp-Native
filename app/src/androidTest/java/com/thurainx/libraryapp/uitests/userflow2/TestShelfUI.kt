package com.thurainx.libraryapp.uitests.userflow2

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.activities.BasedActivity
import com.thurainx.libraryapp.uitests.utils.selectTabAtPosition
import kotlinx.android.synthetic.main.fragment_library.view.*
import org.junit.After
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class TestShelfUI {
    @get:Rule
    val rule = activityScenarioRule<BasedActivity>()

    @After
    fun cleanUp(){
        rule.scenario.close()
    }

    @Test
    fun t1_launch_showEmptyShelfView(){
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))

        Thread.sleep(1000L)
        Espresso.onView(withId(R.id.layoutEmptyShelfList))
            .check(matches(isDisplayed()))
    }

    @Test
    fun t2_tapCreate_showCreatedShelf(){
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))

        Thread.sleep(1000L)
        Espresso.onView(withId(R.id.btnCreateShelf))
            .perform(click())

        Thread.sleep(1000L)
        Espresso.onView(withId(R.id.tvCreateShelfName))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.edtShelfName)).perform(
            ViewActions.typeText(TEST_SHELF_NAME),
            pressImeActionButton()
        )
        Thread.sleep(1000L)


        Espresso.onView(withId(R.id.rvShelves))
            .check(matches(hasDescendant(withText(TEST_SHELF_NAME))))

    }

    @Test
    fun t3_addThreeBook_changeShelfBookCount(){
        Espresso.onView(withId(R.id.navLibrary))
            .perform(click())

        // add first book
        Thread.sleep(1000L)
        Espresso.onView(RecyclerViewMatcher(R.id.rvSmartBookList).viewHolderViewAtPosition(2,R.id.ivBookListViewMore))
            .perform(click())

        checkDialogContents()
        Espresso.onView(withId(R.id.layoutAddToShelf))
            .perform(click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tvAddToShelfTitle))
            .check(matches(isDisplayed()))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvAddToShelf).viewHolderViewAtPosition(0,R.id.cbAddToShelf))
            .perform(click())

        Espresso.onView(withId(R.id.ivAddToShelSave))
            .perform(click())
        Thread.sleep(1000L)

        // add second book
        Espresso.onView(RecyclerViewMatcher(R.id.rvSmartBookList).viewHolderViewAtPosition(1,R.id.ivBookListViewMore))
            .perform(click())

        checkDialogContents()
        Espresso.onView(withId(R.id.layoutAddToShelf))
            .perform(click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tvAddToShelfTitle))
            .check(matches(isDisplayed()))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvAddToShelf).viewHolderViewAtPosition(0,R.id.cbAddToShelf))
            .perform(click())

        Espresso.onView(withId(R.id.ivAddToShelSave))
            .perform(click())
        Thread.sleep(1000L)


        // add third book
        Espresso.onView(RecyclerViewMatcher(R.id.rvSmartBookList).viewHolderViewAtPosition(0,R.id.ivBookListViewMore))
            .perform(click())

        checkDialogContents()
        Espresso.onView(withId(R.id.layoutAddToShelf))
            .perform(click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tvAddToShelfTitle))
            .check(matches(isDisplayed()))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvAddToShelf).viewHolderViewAtPosition(0,R.id.cbAddToShelf))
            .perform(click())

        Espresso.onView(withId(R.id.ivAddToShelSave))
            .perform(click())

        // check book count
        Espresso.onView(withId(R.id.tabLayoutLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .check(matches(hasDescendant(withText(TEST_SHELF_NAME))))




    }

    private fun checkDialogContents(){
        Espresso.onView(withId(R.id.layoutRemoveDownload))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutRemoveFromLibrary))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutAddToWishList))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutAddToShelf))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutAboutTheBook))
            .check(matches(isDisplayed()))
    }

    companion object{
        val TEST_SHELF_NAME = "Fun Books"
    }
}