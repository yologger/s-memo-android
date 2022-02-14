package com.yologger.smemo.ui.screen.create

import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith

import com.yologger.smemo.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.After
import org.junit.Before
import org.junit.Test

@LargeTest
@RunWith(AndroidJUnit4::class)
class CreateActivityTest {

    private lateinit var scenario: ActivityScenario<CreateActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(CreateActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun teardown() {

    }

    @Test
    fun onClose() {
        onView(allOf(instanceOf(AppCompatImageButton::class.java), withParent(withId(R.id.activity_create_tb))))
            .perform(click())
    }

    @Test
    fun onSave_whenInputsInvalid() {
        onView(withId(R.id.activity_create_tv_title)).perform(clearText())
        onView(withId(R.id.activity_create_tv_content)).perform(clearText())
        onView(withId(R.id.activity_create_tb_save)).perform(click())
    }

    @Test
    fun onSave_whenInputsValid() {
        onView(withId(R.id.activity_create_tv_title)).perform(typeText("This is a title."))
        onView(withId(R.id.activity_create_tb_save)).perform(click())
    }
}