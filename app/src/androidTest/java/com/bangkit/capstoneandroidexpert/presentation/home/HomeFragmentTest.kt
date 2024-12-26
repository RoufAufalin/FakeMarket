package com.bangkit.capstoneandroidexpert.presentation.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.bangkit.capstoneandroidexpert.MainActivity
import com.bangkit.capstoneandroidexpert.R
import com.bangkit.capstoneandroidexpert.presentation.detail.DetailActivity
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeFragmentTest {

    @Before
    fun setUp() {
        Intents.init()
        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun whenItemClicked_navigatesToDetailActivity() {
        onView(withId(R.id.rv_product))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )

        Intents.intended(hasComponent(DetailActivity::class.java.name))
    }
}