package de.uni_stuttgart.sopra_ws1617_team8.tong;


import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingRootException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.RatingBar;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * creates a Person, checks the Values
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateAndDeletePersonTest {

    static String TITLE = "Frau";
    static String NAME = "Jen";
    static String SURNAME = "Müller";
    static String REGION = "Stuttgart";
    static String COMPANY = "Bosch";
    static float RATING = 0.5f;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createAndDeletePerson() {
        createPerson(TITLE,NAME,SURNAME,REGION,COMPANY);
        //ViewInteraction recyclerView = onView(
        //        withId(R.id.rv_recycler_view));
        //recyclerView.perform(actionOnItemAtPosition(0, click()));
        Espresso.onView(withText("Abspielen")).perform(click());
        Espresso.onView(withText(NAME)).perform(click());
        Espresso.onView(withId(R.id.textGivenName)).check(matches(withText(NAME)));
        Espresso.onView(withId(R.id.textSurName)).check(matches(withText(SURNAME)));
        Espresso.onView(withId(R.id.textCompany)).check(matches(withText(COMPANY)));
        Espresso.onView(withId(R.id.textRegion)).check(matches(withText(REGION)));

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.iBPlayButton), withContentDescription("Image of the PlayButton"), isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.iBPauseButton), isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withId(R.id.iBStopButton), isDisplayed()));
        appCompatImageButton5.perform(click());
        ViewInteraction appCompatImageButton6 = onView(
                allOf(withId(R.id.iBSuggest), isDisplayed()));
        appCompatImageButton6.perform(click());
        pressBack();
        deletePerson(NAME);
    }
    public static void deletePerson(String name){
        Espresso.onView(withText("Abspielen")).perform(click());
        Espresso.onView(withText(name)).perform(click());
        Espresso.onView(withId(R.id.iBDelete)).perform(click());
        try{
            Espresso.onView(withText(name)).perform(click());
        } catch (NoMatchingViewException e){
            e.printStackTrace();
        }
    }
    public static void createPerson(String title, String name, String surname, String region, String company){
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.iBStartRecord), withContentDescription("Image of the Record Button"), isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.iBStartRecord), withContentDescription("Image of the Record Button"), isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.iBSaveRecord), withContentDescription("Image of the Save Button"), isDisplayed()));
        appCompatImageButton4.perform(click());

        //ViewInteraction appCompatSpinner = onView(
        //        allOf(withId(R.id.spinTitle), isDisplayed()));
        //appCompatSpinner.perform(click());

        Espresso.onView(withId(R.id.spinTitle)).perform(click());
        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText(title), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edGivenName), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edGivenName), isDisplayed()));
        appCompatEditText2.perform(replaceText(name), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.edSurName), isDisplayed()));
        appCompatEditText3.perform(replaceText(surname), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.edRegion), isDisplayed()));
        appCompatEditText4.perform(replaceText(region), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.edCompany), isDisplayed()));
        appCompatEditText5.perform(replaceText(company), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnSave), withText("Ok"), isDisplayed()));
        appCompatButton.perform(click());
    }

}
