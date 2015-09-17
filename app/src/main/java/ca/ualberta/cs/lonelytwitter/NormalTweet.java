package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by ozero on 9/16/15.
 */
public class NormalTweet extends Tweet {
    public NormalTweet(String tweet, Date date) throws IOException {
        super(tweet, date);
    }

    public NormalTweet(String tweet) throws IOException {
        super(tweet);
    }

    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
