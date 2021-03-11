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

}