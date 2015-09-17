package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by ozero on 9/16/15.
 */
public abstract class Tweet implements Tweetable {
    private String text;
    protected Date date;

    // Constructors
    public Tweet(String tweet, Date date) {
        this.setText(tweet);
        this.date = date;
    }

    public Tweet(String tweet) {
        this.setText(tweet);
        this.date = new Date(); // defaults to current date
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        // Max tweet length to 140 chars
        if (text.length() <= 140) {
            this.text = text;
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
