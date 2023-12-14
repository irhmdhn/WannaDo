package com.example.wannado.model;

import java.io.Serializable;

public class ReminderModel implements Serializable {
    public String title;
    public String date;
    public String time;
//    public String repeat;

    public ReminderModel(String title, String date, String time) {
        this.title = title;
        this.date = date;
        this.time = time;
//        this.repeat = repeat;
    }

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

//    public String getRepeat() {
//        return repeat;
//    }
//
//    public void setRepeat(String repeat) {
//        this.repeat = repeat;
//    }
}
