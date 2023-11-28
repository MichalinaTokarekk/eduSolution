package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.*;
import com.eduSolution.eduSolution.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;

    @Autowired
    private UserRepository userRepository;

    public Event saveEvent (Event event){

        return eventRepository.save(event);
    }

    public List<Event> saveEvents (List <Event> events){
        return eventRepository.saveAll(events);
    }
    public  Event getEventId (int id){
        return eventRepository.findById(id).orElse(null);
    }

    public  List<Event> getEvents (){
        return eventRepository.findAll();
    }

    public  Event getEventByName (String name){
        return eventRepository.findByName(name);
    }

    public Event updateEvent (Event event){
        Event existingEvent = eventRepository.findById(event.getId()).orElse(null);
        existingEvent.setName(event.getName());

        return eventRepository.save(existingEvent);
    }

    public DeleteResponseDTO deleteEvent(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        Event event = eventRepository.findById(id).orElse(null);
        eventRepository.deleteById(id);
//        return "Grupa " + name + " został usunięty";
        return event != null ? new DeleteResponseDTO(event.getId(), event.getName()) : null;
    }
}
