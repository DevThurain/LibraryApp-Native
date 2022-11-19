package com.thurainx.libraryapp.uitests.userflow3

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.thurainx.libraryapp.activities.BasedActivity
import org.junit.After
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.OrderWith
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import com.thurainx.libraryapp.R
import com.thurainx.libraryapp.adapters.SmartBookAdapter
import com.thurainx.libraryapp.viewholders.SmartBookViewHolder

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class TestSearchUI {
    @get:Rule
    val rule = activityScenarioRule<BasedActivity>()

    @After
    fun cleanUp() {
        rule.scenario.close()
    }

    @Test
    fun t1_launch_navigateToSearchUI() {
        Espresso.onView(withId(R.id.vpHomeSearch))
            .perform(click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.edtBookSearch))
            .check(matches(isDisplayed()))

    }

    @Test
    fun t2_search_showBookList() {
        Espresso.onView(withId(R.id.vpHomeSearch))
            .perform(click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.edtBookSearch))
            .perform(
                typeText("flutter"),
                closeSoftKeyboard()
            )

        Thread.sleep(3000L)

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(hasDescendant(withText("Beginning Flutter"))))

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(hasDescendant(withText("Flutter in Action"))))

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(hasDescendant(withText("Flutter For Dummies"))))

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(hasDescendant(withText("Flutter Cookbook"))))

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(hasDescendant(withText("Flutter for Beginners"))))

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(hasDescendant(withText("Beginning App Development with Flutter"))))

        Espresso.onView(withId(R.id.rvSearchBook))
            .perform(RecyclerViewActions.scrollToPosition<SmartBookViewHolder>(9))

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(hasDescendant(withText("Practical Flutter"))))


        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(hasDescendant(withText("Flutter Recipes"))))

//        Espresso.onView(withId(R.id.rvSearchBook))
//            .check(matches(hasDescendant(withText("Flutter Complete Reference"))))
//
//        Espresso.onView(withId(R.id.rvSearchBook))
//            .check(matches(hasDescendant(withText("Flutter for Beginners - Second Edition"))))
    }
}