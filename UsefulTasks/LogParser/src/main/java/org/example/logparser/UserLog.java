package org.example.logparser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserLog {
    private String IP;
    private String userName;
    private Date data;
    private Event event;
    private int task;
    private Status status;

    public UserLog(String[] strings) {
        this.IP = strings[0];
        this.userName = strings[1];
        try {
            this.data = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).parse(strings[2]);
        } catch (Exception e) {}
        String[] eventArray = strings[3].split("\\s");
        if (eventArray.length == 1)
            this.event = Event.valueOf(strings[3]);
        else {
            this.event = Event.valueOf(eventArray[0]);
            this.task = Integer.parseInt(eventArray[1]);
        }
        this.status = Status.valueOf(strings[4]);
    }

    public String getIP() {
        return IP;
    }

    public String getUserName() {
        return userName;
    }

    public Date getData() {
        return data;
    }

    public Event getEvent() {
        return event;
    }

    public int getTask() {
        return task;
    }

    public Status getStatus() {
        return status;
    }
}
