package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Event;
import com.eduSolution.eduSolution.service.ClassGroupService;
import com.eduSolution.eduSolution.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/event-controller")
@RestController
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping("/addEvent")
    public Event addEvent (@RequestBody Event event){
        return eventService.saveEvent(event);
    }
    @PostMapping("/addEvents")
    public List<Event> addEvents (@RequestBody List<Event> events){
        return eventService.saveEvents(events);
    }
    @GetMapping("/events")
    public List<Event> findAllEvents() {
        return eventService.getEvents();
    }
    @GetMapping ("/event/{id}")
    public Event findEventId(@PathVariable int id) {
        return eventService.getEventId(id);
    }
    @GetMapping ("/eventName/{name}")
    public Event findEventByName(@PathVariable String name) {
        return eventService.getEventByName(name);
    }

    @PutMapping("/updateEvent")
    public Event updateEvent (@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public DeleteResponseDTO deleteEvent(@PathVariable int id) {
        return eventService.deleteEvent(id);
    }
}
