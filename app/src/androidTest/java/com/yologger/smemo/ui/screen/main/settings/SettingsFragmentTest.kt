package com.yologger.smemo.ui.screen.main.settings

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsFragmentTest {

    private lateinit var scenario: FragmentScenario<SettingsFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.STARTED)
    }
}