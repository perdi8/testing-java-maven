package com.example.demo.service;

import com.example.demo.Attendee;
import com.example.demo.Event;
import com.example.demo.EventType;
import com.example.demo.service.EventNotificationService;
import com.example.demo.service.EventNotificationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventNotificationServiceImplTest {

    private static final String MSG_ANNOUNCE = "The next big event is coming!";

    @Mock
    EventNotificationService eventNotificationService;

    @InjectMocks
    EventNotificationServiceImpl eventNotificationServiceImpl;

    @Test
    @DisplayName("when I call the announce method and I pass an event to it, it should notify all attendees with a message and verify that the class is called once")
    void announce() {
        Event event = new Event(1l,"DeveloperCongress", EventType.TECH, eventNotificationService);
        Attendee attendee1 = new Attendee(1l,"Miguel","miguel@email.com");
        Attendee attendee2 = new Attendee(2l,"Manuel","manuel@email.com");

        ArgumentCaptor<Event> argumentCaptor = ArgumentCaptor.forClass(Event.class);
        List<Attendee> attendees = new ArrayList<>();

        attendees.add(attendee1);
        attendees.add(attendee2);

        event.addAttendees(attendees);

        eventNotificationService.announce(event);
        eventNotificationServiceImpl.announce(event);

        verify(eventNotificationService, times(1)).announce(argumentCaptor.capture());
        assertEquals(MSG_ANNOUNCE, attendee1.getNotifications().get(0).getMessage());

    }



}