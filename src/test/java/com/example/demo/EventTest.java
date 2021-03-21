package com.example.demo;

import com.example.demo.Attendee;
import com.example.demo.Event;
import com.example.demo.Speaker;
import com.example.demo.service.EventNotificationService;
import com.example.demo.service.EventNotificationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.EventType.TECH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventTest {

    @Mock
    EventNotificationService eventNotificationService;


    @Test
    @DisplayName("the list shouldn't appear empty when I add an assistant")
    void EventConstructorSetterAndGetter() {
        Event event = new Event(1l,"developer",TECH,eventNotificationService);
        List speakers = new ArrayList<Speaker>();
        Speaker speaker = new Speaker(1l,"miguel","developer");
        speakers.add(speaker);
        event.setId(2l);
        event.setTitle("developer");
        event.setType(TECH);
        event.setSpeakers(speakers);

        assertEquals(event.getId(), 2l);
        assertEquals(event.getTitle(), "developer");
        assertEquals(event.getType(), TECH);

        assertTrue(event !=null);
    }

    @Test
    @DisplayName("the list shouldn't appear empty and should have the same nickname when I add an assistant")
    void addAttendee() {

        Event event = new Event();
        Attendee attendee = new Attendee();

        attendee.setId(1l);
        attendee.setNickname("Carola");
        attendee.setEmail("carola@email.com");
        event.addAttendee(attendee);

        assertTrue(event.getAttendees().size()==1);
        assertEquals("Carola", event.getAttendees().get(0).getNickname());

    }

    @Test
    @DisplayName("the list should have a second assistant when I add it")
    void addAttendee2() {

        Event event = new Event();
        Attendee attendee = new Attendee(1l,"Miguel","miguel@email.com");
        Attendee attendee2 = new Attendee(2l,"Manuel","manuel@email.com");

        event.addAttendee(attendee);
        event.addAttendee(attendee2);

        assertEquals(attendee2,event.getAttendees().get(1));

    }

    @Test
    @DisplayName("the list shouldn't contain attendee if I don't add it")
    void notAddAttendee() {

        Event event = new Event();
        Attendee attendee = new Attendee();

        assertTrue(!event.getAttendees().contains(attendee));

    }

    @Test
    @DisplayName("I should add an attendee to the attendee list")
    void addAttendees() {

        Event event = new Event();
        Attendee attendee = new Attendee(1l,"Miguel","miguel@email.com");
        Attendee attendee2 = new Attendee(2l,"Cristina","cristina@email.com");
        List<Attendee> attendees = new ArrayList<>();

        attendees.add(attendee);
        attendees.add(attendee2);
        event.addAttendees(attendees);

        assertTrue(event.getAttendees().get(0)!=null);
        assertTrue(event.getAttendees().get(1)!=null);
        assertTrue(attendees.size()==2);

    }

    @Test
    @DisplayName("it should show null when I add a null attendee")
    void addAttendeesWithNull() {

        Event event = new Event();
        List<Attendee> attendees = new ArrayList<>();

        attendees.add(null);
        event.addAttendees(attendees);

        assertTrue(event.getAttendees().get(0)==null);

    }

    @Test
    @DisplayName("it shouldn't show a attendee when i remove it, it should return an empty list")
    void removeAttendee() {

        Event event = new Event();
        Attendee attendee = new Attendee(1l,"Miguel","miguel@email.com");

        event.addAttendee(attendee);
        event.removeAttendee(attendee);

        assertTrue(event.getAttendees().size()==0);
        assertEquals( new ArrayList<>() , event.getAttendees());

    }

    @Test
    @DisplayName("it shouldn't show attendees when I delete the entire list")
    void removeAttendees() {

        Event event = new Event();
        Attendee attendee = new Attendee(1l,"Miguel","miguel@email.com");
        Attendee attendee2 = new Attendee(2l,"Manuel","manuel@email.com");
        List<Attendee> attendees = new ArrayList<>();

        attendees.add(attendee);
        attendees.add(attendee2);
        event.removeAttendees(attendees);

        assertTrue(event.getAttendees().size()==0);

    }

    @Test
    @DisplayName("when an event emits a notification, it is verified that the method: announce, of the interface class is called once")
    void notifyAssistants() {

        Event event = new Event(1l,"developer", TECH, eventNotificationService);
        ArgumentCaptor<Event> argumentCaptor = ArgumentCaptor.forClass(Event.class);

        event.notifyAssistants();

        verify(eventNotificationService, times(1)).announce(argumentCaptor.capture());
        assertEquals(event, argumentCaptor.getValue());

    }

    @Test
    @DisplayName("It should show two speakers when you add them and I check that they are different and that the list is not empty")
    void addSpeaker() {

        Speaker speaker = new Speaker();
        Speaker speaker2 = new Speaker(2l,"Cristina","developer");
        Event event = new Event();

        speaker.setId(1l);
        speaker.setName("Carola");
        speaker.setExpertise("developer");

        event.addSpeaker(speaker);
        event.addSpeaker(speaker2);
        speaker.equals(speaker2);

        assertFalse(speaker.getId() == speaker2.getId());
        assertFalse(event.getSpeakers().isEmpty());

    }

    @Test
    @DisplayName("It shouldn't show a speaker when I remove it from the list")
    void removeSpeaker() {

        Event event = new Event();
        Speaker speaker = new Speaker(1l,"miguel","developer");

        event.addSpeaker(speaker);
        assertTrue(event.getSpeakers().size()==1);

        event.removeSpeaker(speaker);
        assertFalse(event.getSpeakers().size()!=0);
    }

}