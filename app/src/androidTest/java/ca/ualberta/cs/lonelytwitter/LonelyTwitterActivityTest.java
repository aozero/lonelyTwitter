package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private Button saveButton;
    private EditText bodyText;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testEditTweet() {
        // When you call getActivity android will start the app and the activity
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        // Reset the app to a known state
        activity.getTweets().clear();

        // Add a tweet using the UI
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("test tweet");
            }
        });
        getInstrumentation().waitForIdleSync();

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        // Make sure the tweet was actually added
        final ListView oldTweetList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetList.getItemAtPosition(0);
        assertEquals("test tweet", tweet.getText());

        // Ensure the tweet editor activity starts up
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        // Click on the tweet to edit
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetList.getChildAt(0);
                oldTweetList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // Validate that ReceiverActivity is started
        // https://developer.android.com/training/activity-testing/activity-functional-testing.html
        // 2015-10-14
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // Test that the editor starts up with the right tweet in it to edit

        // Test that we can edit that tweet

        // Test that we can push some kind of save button for that tweet

        // Test that new modified tweet text was actually saved

        // Test that the new modified tweet text is displayed on the other activity


        // Clean up our activities at the end of our test
        receiverActivity.finish();
    }
}