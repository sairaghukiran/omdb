package com.example.srk.openmoviedatabase.adapter;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.srk.openmoviedatabase.R;
import com.example.srk.openmoviedatabase.activity.DisplayMoviesActivity;
import com.example.srk.openmoviedatabase.model.Movie;
import com.example.srk.openmoviedatabase.viewholder.MovieViewHolder;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Raghu on 10/31/2016.
 */
@RunWith(AndroidJUnit4.class)
public class MoviesAdapterInstrumentationTest {

    @Rule
    public ActivityTestRule<DisplayMoviesActivity> mActivityRule = new ActivityTestRule<>(
            DisplayMoviesActivity.class);

    @Before
    public void performClick() throws Exception {
        onView(withId(R.id.search_box)).perform(typeText("Batman"), closeSoftKeyboard());
        onView(withId(R.id.search_button)).perform(click());
    }

    @Test
    public void testRecyclerViewVisible() throws Exception {
        onView(withText("1")).check(matches(isDisplayed()));
    }

    @Test
    public void testResultHasQueryText() throws Exception {
        onView(allOf(
                withText("5"), hasSibling(anyOf(withText(containsString("Batman")), withText(containsString("batman"))))
                )).check(matches(isDisplayed()));
    }
}