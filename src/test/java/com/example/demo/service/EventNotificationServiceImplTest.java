package com.example.demo.service;

import com.example.demo.Attendee;
import com.example.demo.Event;
import com.example.demo.EventType;
import com.example.demo.Notification;
import com.example.demo.service.EventNotificationService;
import com.example.demo.service.EventNotificationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventNotificationServiceImplTest {

    private static final String MSG_ANNOUNCE = "The next big event is coming!";


    @Captor
    ArgumentCaptor<Notification>notificationCaptor;

    @Mock
    EventNotificationService eventNotificationService;

    @InjectMocks
    Event event;

    @Test
    @DisplayName("when I call the announce method and I pass an event to it, it should notify all attendees with a message and verify that the class is called once")
    void announce() {
        Event event = new Event(1l,"DeveloperCongress", EventType.TECH, eventNotificationService);
        ArgumentCaptor<Event> argumentCaptor = ArgumentCaptor.forClass(Event.class);

//        Attendee attendee = new Attendee(1l,"m","m@m.com");
//        List<Attendee> asistentes = new ArrayList<>();
//
//        event.addAttendee(attendee);
//
//        asistentes.add(attendee);
//        event.addAttendees(asistentes);
//        Notification notify = new Notification();
//        EventNotificationServiceImpl eventNotify = new EventNotificationServiceImpl();

        eventNotificationService.announce(event);


        verify(eventNotificationService, times(1)).announce(argumentCaptor.capture());
        assertEquals(event, argumentCaptor.getValue());

    }

    @Test
    @DisplayName("when I call the confirm attendance method and pass an event and attendance to it, it should notify all attendees with a message and the event, I verify that the class is called once")
    void confirmAttendance() {

        Event event = new Event(1l,"DeveloperCongress", EventType.TECH,eventNotificationService);
        Attendee attendee1 = new Attendee(1l,"Miguel","miguel@email.com");

        ArgumentCaptor<Event> argumentCaptorEvent = ArgumentCaptor.forClass(Event.class);
        ArgumentCaptor<Attendee> argumentCaptorAttendee = ArgumentCaptor.forClass(Attendee.class);

        eventNotificationService.confirmAttendance(event, attendee1);

        verify(eventNotificationService, times(1)).confirmAttendance(argumentCaptorEvent.capture(),argumentCaptorAttendee.capture());
        assertEquals(event, argumentCaptorEvent.getValue());
        assertEquals(attendee1, argumentCaptorAttendee.getValue());
    }
}