package edu.towson.cosc431.collinwoodruff.todosapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void AddTodoButtonDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withText("New Todo")).check(matches(isDisplayed()));
    }

    @Test
    public void ImageButtonDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withText("Upload Image")).check(matches(isDisplayed()));
    }

    @Test
    public void IsCompleteCheckboxDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withText("Completed")).check(matches(isDisplayed()));
    }
}
