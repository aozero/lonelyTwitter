package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by ozero on 9/16/15.
 */
public abstract class Tweet implements Tweetable {
    private String text;
    protected Date date;

    // Constructors
    public Tweet(String tweet, Date date) throws IOException {
        this.setText(tweet);
        this.date = date;
    }

    public Tweet(String tweet) throws IOException {
        this.setText(tweet);
        this.date = new Date(); // defaults to current date
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) throws IOException {
        // Max tweet length to 140 chars
        if (text.length() <= 140) {
            this.text = text;
        } else {
            throw new IOException("Tweet was too long!");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();
}
