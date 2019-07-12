package com.resume.davinder

import android.arch.lifecycle.ViewModelProviders
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.resume.davinder.utils.SimpleIdlingResource
import com.resume.davinder.views.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import org.junit.After
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.v7.widget.RecyclerView
import android.view.View
import com.resume.davinder.injection.ViewModelInjector
import com.resume.davinder.model.Education
import com.resume.davinder.viewmodels.ResumeViewModel
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test


@RunWith(AndroidJUnit4::class)
@LargeTest
class DataLoadMainActivityTest {
    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    lateinit var idlingResource:SimpleIdlingResource

    @Before
    fun setUp() {
        val activity= activityTestRule.activity
        activity as MainActivity
        val viewModel= ViewModelProviders.of(activity).get(ResumeViewModel::class.java)
        idlingResource= viewModel.idlingResource
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    fun validate_dataloads(){
        onView(withId(R.id.rvEducation)).check(matches(withItemsLoaded()))
        onView(withId(R.id.rvApps)).check(matches(withItemsLoaded()))
        onView(withId(R.id.rvWorkExperience)).check(matches(withItemsLoaded()))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    companion object {

        fun withItemsLoaded():Matcher<View> {

            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("RecyclerView with item count: ")
                }


                override fun matchesSafely(item: RecyclerView?): Boolean {
                    val itemCount=item?.adapter?.itemCount
                    if(itemCount==null)
                        return false
                    return itemCount>0
                }
            }
        }
    }

}