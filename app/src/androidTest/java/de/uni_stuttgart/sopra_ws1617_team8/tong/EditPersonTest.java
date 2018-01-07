package de.uni_stuttgart.sopra_ws1617_team8.tong;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditPersonTest {

    static String TITLE = "Herr";
    static String NAME = "Jens";
    static String SURNAME = "Müller";
    static String REGION = "Stuttgart";
    static String COMPANY = "Bosch";

    static String NEWTITLE = "Frau";
    static String NEWNAME = "Jensi";
    static String NEWSURNAME = "Mülleri";
    static String NEWREGION = "Stuttgartzwei";
    static String NEWCOMPANY = "Daimler";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void editPerson() {
        CreateAndDeletePersonTest.createPerson(TITLE,NAME,SURNAME,REGION,COMPANY);
        ViewInteraction appCompatTextView5 = onView(
                allOf(withText("Abspielen"), isDisplayed()));
        appCompatTextView5.perform(click());

        Espresso.onView(withText(NAME)).perform(click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withId(R.id.iBEdit), withContentDescription("Edit Button"), isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinTitle), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(android.R.id.text1), withText(NEWTITLE), isDisplayed()));
        appCompatTextView6.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.edGivName), isDisplayed()));
        appCompatEditText7.perform(replaceText(NEWNAME), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.edSurName), isDisplayed()));
        appCompatEditText8.perform(replaceText(NEWSURNAME), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.edReg), isDisplayed()));
        appCompatEditText9.perform(replaceText(NEWREGION), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.edCom), isDisplayed()));
        appCompatEditText10.perform(replaceText(NEWCOMPANY), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnEditSubmit), withText("Eintragen"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withContentDescription("Nach oben"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        appCompatImageButton7.perform(click());
        Espresso.onView(withText("Abspielen")).perform(click());
        Espresso.onView(withText(NEWNAME)).perform(click());
        Espresso.onView(withId(R.id.textGivenName)).check(matches(withText(NEWNAME)));
        Espresso.onView(withId(R.id.textSurName)).check(matches(withText(NEWSURNAME)));
        Espresso.onView(withId(R.id.textCompany)).check(matches(withText(NEWCOMPANY)));
        Espresso.onView(withId(R.id.textRegion)).check(matches(withText(NEWREGION)));
        pressBack();
        CreateAndDeletePersonTest.deletePerson(NEWNAME);
    }

}
