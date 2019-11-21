package com.dicoding.picodiploma.sejiwaproject.features.match.search

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchMatchActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SearchMatchActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun testSearchMatchWithQueryArsenal() {
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Arsenal"),
            pressImeActionButton()
        )
        onView(withId(R.id.rv_search_match)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                1
            )
        )
        onView(withId(R.id.rv_search_match)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )

        pressBack()
    }

    @Test
    fun testSearchMatchWithQueryChelsea() {
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Chelsea"),
            pressImeActionButton()
        )
        onView(withId(R.id.rv_search_match)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                1
            )
        )
        onView(withId(R.id.rv_search_match)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )

        pressBack()

    }
}