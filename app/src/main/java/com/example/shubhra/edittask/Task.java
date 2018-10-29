package com.example.shubhra.edittask;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Aliandro on 2/3/2018.
 */

public class Task implements Serializable {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    String title;
    String date;
    String time;
    String priority;

    public Task(String title, String date, String time, String priority) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.priority = priority;
    }
}
