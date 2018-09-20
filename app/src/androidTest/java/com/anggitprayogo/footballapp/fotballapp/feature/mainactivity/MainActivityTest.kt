package com.anggitprayogo.footballapp.fotballapp.feature.mainactivity

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.anggitprayogo.footballapp.fotballapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testScheduleRecyclerBehavior() {

        Thread.sleep(5000)

        //Cek apakah recycelrview dengan id rv_prev_schedule muncul
        Espresso.onView(ViewMatchers.withId(R.id.rv_league))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.rv_league))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))

        Espresso.onView(ViewMatchers.withId(R.id.rv_league))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))



        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.tv_date))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }

}