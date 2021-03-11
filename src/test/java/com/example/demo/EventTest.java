package com.example.demo;

import com.example.demo.Attendee;
import com.example.demo.Event;
import com.example.demo.Speaker;
import com.example.demo.service.EventNotificationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    EventNotificationServiceImpl eventNotificationService;


    @Test
    @DisplayName("the list shouldn't appear empty when I add an assistant")
    void EventConstructorSetterAndGetter() {
        Event evento = new Event(1l,"developer",TECH,eventNotificationService);
        List speakers = new ArrayList<Speaker>();
        Speaker speaker = new Speaker(1l,"miguel","developer");
        speakers.add(speaker);
        evento.setId(2l);
        evento.setTitle("developer");
        evento.setType(TECH);
        evento.setSpeakers(speakers);

        assertEquals(evento.getId(), 2l);
        assertEquals(evento.getTitle(), "developer");
        assertEquals(evento.getType(), TECH);

        assertTrue(evento !=null);
    }

    @Test
    @DisplayName("the list shouldn't appear empty and should have the same nickname when I add an assistant")
    void addAttendee() {

        Event evento = new Event();
        Attendee asistente = new Attendee();

        asistente.setId(1l);
        asistente.setNickname("Carola");
        asistente.setEmail("carola@email.com");
        evento.addAttendee(asistente);

        assertTrue(evento.getAttendees().size()==1);
        assertEquals("Carola", evento.getAttendees().get(0).getNickname());

    }

    @Test
    @DisplayName("the list should have a second assistant when I add it")
    void addAttendee2() {

        Event evento = new Event();
        Attendee asistente = new Attendee(1l,"Miguel","miguel@email.com");
        Attendee asistente2 = new Attendee(2l,"Manuel","manuel@email.com");

        evento.addAttendee(asistente);
        evento.addAttendee(asistente2);

        assertEquals(asistente2,evento.getAttendees().get(1));

    }

    @Test
    @DisplayName("the list shouldn't contain attendee if I don't add it")
    void notAddAttendee() {

        Event evento = new Event();
        Attendee asistente = new Attendee();

        assertTrue(!evento.getAttendees().contains(asistente));

    }

    @Test
    @DisplayName("I should add an attendee to the attendee list")
    void addAttendees() {

        Event evento = new Event();
        Attendee asistente = new Attendee(1l,"Miguel","miguel@email.com");
        Attendee asistente2 = new Attendee(2l,"Cristina","cristina@email.com");
        List<Attendee> asistentes = new ArrayList<>();

        asistentes.add(asistente);
        asistentes.add(asistente2);
        evento.addAttendees(asistentes);

        assertTrue(evento.getAttendees().get(0)!=null);
        assertTrue(evento.getAttendees().get(1)!=null);
        assertTrue(asistentes.size()==2);

    }

    @Test
    @DisplayName("it should show null when I add a null attendee")
    void addAttendeesWithNull() {

        Event evento = new Event();
        List<Attendee> asistentes = new ArrayList<>();

        asistentes.add(null);
        evento.addAttendees(asistentes);

        assertTrue(evento.getAttendees().get(0)==null);

    }

    @Test
    @DisplayName("it shouldn't show a attendee when i remove it, it should return an empty list")
    void removeAttendee() {

        Event evento = new Event();
        Attendee asistente = new Attendee(1l,"Miguel","miguel@email.com");

        evento.addAttendee(asistente);
        evento.removeAttendee(asistente);

        assertTrue(evento.getAttendees().size()==0);
        assertEquals( new ArrayList<>() , evento.getAttendees());

    }

    @Test
    @DisplayName("it shouldn't show attendees when I delete the entire list")
    void removeAttendees() {

        Event evento = new Event();
        Attendee asistente = new Attendee(1l,"Miguel","miguel@email.com");
        Attendee asistente2 = new Attendee(2l,"Manuel","manuel@email.com");
        List<Attendee> asistentes = new ArrayList<>();

        asistentes.add(asistente);
        asistentes.add(asistente2);
        evento.removeAttendees(asistentes);

        assertTrue(evento.getAttendees().size()==0);

    }

    @Test
    @DisplayName("when an event emits a notification, it is verified that the method: announce, of the interface class is called once")
    void notifyAssistants() {

        Event evento = new Event(1l,"developer", TECH, eventNotificationService);
        evento.notifyAssistants();

        verify(eventNotificationService, times(1)).announce(evento);

    }

    @Test
    @DisplayName("It should show two speakers when you add them and I check that they are different and that the list is not empty")
    void addSpeaker() {

        Speaker speaker = new Speaker();
        Speaker speaker2 = new Speaker(2l,"Cristina","developer");
        Event evento = new Event();

        speaker.setId(1l);
        speaker.setName("Carola");
        speaker.setExpertise("developer");

        evento.addSpeaker(speaker);
        evento.addSpeaker(speaker2);
        speaker.equals(speaker2);

        assertFalse(speaker.getId() == speaker2.getId());
        assertFalse(evento.getSpeakers().isEmpty());

    }

    @Test
    @DisplayName("It shouldn't show a speaker when I remove it from the list")
    void removeSpeaker() {

        Event evento = new Event();
        Speaker speaker = new Speaker(1l,"miguel","developer");

        evento.addSpeaker(speaker);
        assertTrue(evento.getSpeakers().size()==1);

        evento.removeSpeaker(speaker);
        assertFalse(evento.getSpeakers().size()!=0);
    }

}