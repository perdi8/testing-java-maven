package com.example.demo.service;

import com.example.demo.Attendee;
import com.example.demo.Event;
import com.example.demo.Notification;

public class EventNotificationServiceImpl implements EventNotificationService {


    private static final String MSG_ANNOUNCE = "The next big event is coming!";
    private static final String MSG_CONFIRM = "Dear Attendee, your subscription to the event has been confirmed successfully.";

    @Override
    public void announce(Event event) {
        if (event == null || event.getAttendees() == null || event.getAttendees().isEmpty())
            return;

        for (Attendee attendee : event.getAttendees()) {
            Notification announce = new Notification(MSG_ANNOUNCE);
            attendee.getNotifications().add(announce);
        }

        return;
    }

    @Override
    public void confirmAttendance(Event event, Attendee attendee) {
        if (event == null || attendee == null)
            return;

        Notification confirm = new Notification(MSG_CONFIRM);
        attendee.getNotifications().add(confirm);
    }
}
