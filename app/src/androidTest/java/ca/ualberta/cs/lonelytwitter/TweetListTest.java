package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;


/**
 * Created by ozero on 9/30/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver {
    private boolean wasNotified = false;

    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    public void testDeleteTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HI");
        tweetList.add(tweet);
        tweetList.delete(tweet);
        assertFalse(tweetList.hasTweet(tweet));
    }

    public void testHasTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HI");
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testAddTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HI");
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
        boolean tweetDuplicateFlag = false;
        try{
            if(tweetList.hasTweet(tweet)) {
                throw new IllegalArgumentException();
            } else {
                tweetList.add(tweet);
            }
            tweetList.add(tweet);
        } catch (IllegalArgumentException e) {
            tweetDuplicateFlag = true;
        }
        assertTrue(tweetDuplicateFlag);
    }

    public void testGetCount() {

    }

    public void testGetTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HI");
        Tweet tweet2 = new NormalTweet("HI2");
        Tweet tweet3 = new NormalTweet("HI3");

        tweetList.add(tweet);
        Tweet returnedTweet = tweetList.getTweet(0);
        assertTrue((tweet.date.equals(returnedTweet.date)) &&
                tweet.getText().equals(returnedTweet.getText()));

        tweetList.add(tweet2);
        tweetList.add(tweet3);



    }

    public void testGetTweetType() {

    }

    public void testTweetListChanged() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihi");
        tweetList.addObserver(this);
        wasNotified = false;
        assertFalse(wasNotified);
        tweetList.add(tweet);
        assertTrue(wasNotified);
    }

    public void myNotify() {
        wasNotified = true;
    }
}