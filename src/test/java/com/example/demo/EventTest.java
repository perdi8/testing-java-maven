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

}