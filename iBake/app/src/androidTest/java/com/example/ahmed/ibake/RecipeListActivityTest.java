package com.example.ahmed.ibake;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityTestRule = new ActivityTestRule<>(RecipeListActivity.class);

    @Test
    public void recipeListActivityTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_recipe_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_ingre_details), withText("350.0 G Bittersweet chocolate (60-70% cacao)"),
                        childAtPosition(
                                allOf(withId(R.id.ingredient_card),
                                        childAtPosition(
                                                withId(R.id.rv_recipe_ingredients),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("350.0 G Bittersweet chocolate (60-70% cacao)")));

    }

    @Test
    public void checkIfYellowCakeLaunched() {

        //Find the reference to the recyclerview
        //allof performs a filter on the set of inputs, in this case
        //the id with rv_recipe_list and that is has to be displayed
        ViewInteraction recyclerView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.rv_recipe_list), ViewMatchers.isDisplayed()));

        //This will perform the click on the card with yellow cake text
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition(3, ViewActions.click()));

        //Validate the ingredient text is displayed
        ViewInteraction ingre = Espresso.onView(allOf(withId(R.id.ingre_header), isDisplayed()));
        ingre.check(ViewAssertions.matches(withText(R.string.ingredients)));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
