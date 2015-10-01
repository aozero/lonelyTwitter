package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;


/**
 * Created by ozero on 9/30/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {

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
}