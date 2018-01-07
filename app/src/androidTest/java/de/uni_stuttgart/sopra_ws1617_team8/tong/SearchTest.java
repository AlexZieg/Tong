package de.uni_stuttgart.sopra_ws1617_team8.tong;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.core.deps.guava.util.concurrent.ThreadFactoryBuilder;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchTest {

    String TITLE="Frau";
    String NAME="SuchTestVorname";
    String SURNAME="SuchTestNachname";
    String REGION="Deutschland";
    String COMPANY="Bosch";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchTest() {

        CreateAndDeletePersonTest.createPerson(TITLE,NAME,SURNAME,REGION,COMPANY);
        CreateAndDeletePersonTest.createPerson(TITLE,NAME+"abc",SURNAME+"abc",REGION,COMPANY);


        ViewInteraction appCompatTextView = onView(
                allOf(withText("Abspielen"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.searchData), isDisplayed()));
        appCompatEditText.perform(replaceText("abc"), closeSoftKeyboard());

        Espresso.onView(withText(NAME+"abc")).perform(click());
        Espresso.onView(withId(R.id.textGivenName)).check(matches(withText(NAME+"abc")));
        Espresso.onView(withId(R.id.textSurName)).check(matches(withText(SURNAME+"abc")));
        Espresso.onView(withId(R.id.textCompany)).check(matches(withText(COMPANY)));
        Espresso.onView(withId(R.id.textRegion)).check(matches(withText(REGION)));
        pressBack();


        CreateAndDeletePersonTest.deletePerson(NAME);
        CreateAndDeletePersonTest.deletePerson(NAME+"abc");

    }

}

