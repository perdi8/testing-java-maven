package com.example.demo.service;

import com.example.demo.Attendee;
import com.example.demo.Event;

public interface EventNotificationService {

    public void announce(Event event);

    public void confirmAttendance(Event event, Attendee attendee);

}
