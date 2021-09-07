package com.filippoengidashet.marketproductsapp.mvvm.view.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.filippoengidashet.marketproductsapp.R
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Filippo 23/08/2021
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun openProductScreen_test() {

        //check if button is visible
        onView(withId(R.id.button_tap))
            .check(ViewAssertions.matches(isDisplayed()))

        //tap button to open product list
        onView(withId(R.id.button_tap)).perform(ViewActions.click())

        //verify product list is shown

        onView(allOf(withId(R.id.products_rview), isDisplayed()))
    }

    @After
    fun tearDown() {
    }
}