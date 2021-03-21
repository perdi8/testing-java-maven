package com.example.demo.service;

import com.example.demo.Attendee;
import com.example.demo.Event;
import com.example.demo.EventType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventNotificationServiceImplTest {

    private static final String MSG_ANNOUNCE = "The next big event is coming!";
    private static final String MSG_CONFIRM = "Dear Attendee, your subscription to the event has been confirmed successfully.";

    @Mock
    EventNotificationService eventNotificationService;

    @Test
    @DisplayName("when I call the announce method and I pass an event to it, it should notify all attendees with a message and verify that the class is called once")
    void announce() {

        Event event = new Event(1l,"DeveloperCongress", EventType.TECH, eventNotificationService);
        Attendee attendee = new Attendee(1l,"m","m@m.com");
        List<Attendee> attendees = new ArrayList<>();
        ArgumentCaptor<Event> argumentCaptor = ArgumentCaptor.forClass(Event.class);

        EventNotificationServiceImpl eventNotify = new EventNotificationServiceImpl();

        event.addAttendee(attendee);
        attendees.add(attendee);
        event.addAttendees(attendees);
        event.notifyAssistants();

        eventNotify.announce(event);


        assertEquals(MSG_ANNOUNCE,attendee.getNotifications().get(0).getMessage());

        verify(eventNotificationService, times(1)).announce(argumentCaptor.capture());
        assertEquals(event, argumentCaptor.getValue());
    }

    @Test
    @DisplayName("when I call the confirm attendance method and pass an event and attendance to it, it should notify all attendees with a message and the event, I verify that the class is called once")
    void confirmAttendance() {

        Event event = new Event(1l,"DeveloperCongress", EventType.TECH,eventNotificationService);
        Attendee attendee = new Attendee(1l,"Miguel","miguel@email.com");

        ArgumentCaptor<Event> argumentCaptorEvent = ArgumentCaptor.forClass(Event.class);
        ArgumentCaptor<Attendee> argumentCaptorAttendee = ArgumentCaptor.forClass(Attendee.class);

        EventNotificationServiceImpl eventNotify = new EventNotificationServiceImpl();

        eventNotificationService.confirmAttendance(event, attendee);
        eventNotify.confirmAttendance(event, attendee);

        assertEquals(MSG_CONFIRM,attendee.getNotifications().get(0).getMessage());

        verify(eventNotificationService, times(1)).confirmAttendance(argumentCaptorEvent.capture(),argumentCaptorAttendee.capture());
        assertEquals(event, argumentCaptorEvent.getValue());
        assertEquals(attendee, argumentCaptorAttendee.getValue());
    }
}