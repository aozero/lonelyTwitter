package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by ozero on 9/16/15.
 */
public abstract class CurrentMood {
    private String mood;
    protected Date date;

    // Constructors
    public CurrentMood(String mood, Date date) {
        this.mood = mood;
        this.date = date;
    }

    public CurrentMood(String mood) {
        this.mood = mood;
        this.date = new Date(); // defaults to current date
    }

    // Getters and Setters

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
