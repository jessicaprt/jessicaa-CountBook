package ca.prieto.countbook.Model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Jessica on 2017-09-27.
 */

public class Counter {
    private String name;
    private String id;
    private int initialValue;
    private int currentValue;
    private String comment;
    private Date date;

    public Counter(String name, int initialValue, String comment) {
        this.name = name;
        this.comment = comment;
        this.date = new Date();
        this.currentValue = initialValue;
        this.initialValue = initialValue;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
}
