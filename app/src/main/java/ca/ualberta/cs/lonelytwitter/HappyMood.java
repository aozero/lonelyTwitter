package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by ozero on 9/16/15.
 */
public class HappyMood extends CurrentMood {

    // Constructors
    public HappyMood(String mood, Date date) {
        super(mood, date);
        this.setMood(mood);
        this.date = date;
    }

    public HappyMood(String mood) {
        super(mood);
        this.setMood(mood);
        this.date = new Date();
    }

    public String format() {
        return "Happy";
    }
}
