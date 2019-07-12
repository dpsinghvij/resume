package com.resume.davinder

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.resume.davinder.utils.Constants
import com.resume.davinder.views.MainActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.contains
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun check_NameText() {
        onView(withId(R.id.txtName)).check(matches(withText("Davinder Pal Singh")))
    }

    @Test
    fun check_ifsummaryPresent() {
        onView(withId(R.id.txtSummary)).check(matches(isDisplayed()))
    }

    @Test
    fun validateGithub() {
        Intents.init();
        onView(withId(R.id.action_github)).perform(click())
        intended(
            allOf(
                hasAction(ACTION_VIEW),
                hasData(Constants.GITHUB_URL)

            )
        )
    }


}