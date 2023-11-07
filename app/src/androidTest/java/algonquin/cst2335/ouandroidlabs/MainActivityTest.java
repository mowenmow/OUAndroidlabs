package algonquin.cst2335.ouandroidlabs;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testGoodPsw() {
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordText));
        appCompatEditText.perform(replaceText("wI123*"), closeSoftKeyboard());

        ViewInteraction materialButton = onView((withId(R.id.loginButton)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.responseText));
        textView.check(matches(withText("Your password is complex enough.")));
    }

    @Test
    /**This method is to test if the password misses lower case letters.
     *
     */
    public void testMissingLowerCase() {
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordText));
        appCompatEditText.perform(replaceText("W123*"), closeSoftKeyboard());

        ViewInteraction materialButton = onView((withId(R.id.loginButton)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.responseText));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**This method is to test if the password misses upper case letters.
     *
     */
    @Test
    public void testFindMissingUpperCase() {
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordText));
        appCompatEditText.perform(replaceText("wi123*"));

        ViewInteraction materialButton = onView((withId(R.id.loginButton)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.responseText));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**This method is to test if the password misses digits.
     *
     */
    @Test
    public void testFindMissingDigits() {
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordText));
        appCompatEditText.perform(replaceText("wI*"), closeSoftKeyboard());

        ViewInteraction materialButton = onView((withId(R.id.loginButton)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.responseText));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**This method is to test if the password misses special characters.
     *
     */
    @Test
    public void testFindMissSpeChar() {
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordText));
        appCompatEditText.perform(replaceText("wI123"), closeSoftKeyboard());

        ViewInteraction materialButton = onView((withId(R.id.loginButton)));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.responseText));
        textView.check(matches(withText("You shall not pass!")));
    }
}