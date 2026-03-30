package io.roa.springexceptionhandler.service.impl;

import io.roa.springexceptionhandler.exception.EntityExistsException;
import io.roa.springexceptionhandler.exception.EntityNotFoundException;
import io.roa.springexceptionhandler.model.entity.Event;
import io.roa.springexceptionhandler.model.request.EventRequest;
import io.roa.springexceptionhandler.repository.EventAttendeeRepository;
import io.roa.springexceptionhandler.repository.EventRepository;
import io.roa.springexceptionhandler.service.EventService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;

    public EventServiceImpl(EventRepository eventRepository, EventAttendeeRepository eventAttendeeRepository) {
        this.eventRepository = eventRepository;
        this.eventAttendeeRepository = eventAttendeeRepository;
    }

    @Override
    public Event getEventById(int id) {
        Event event = eventRepository.getEventById(id);
        if (event == null) throw new EntityNotFoundException("Event with id " + id + " not found.");
        return event;
    }

    @Override
    @Transactional
    public Event createEvent(EventRequest request) {
        try {
            Event event = eventRepository.saveEvent(request);
            if (request.getAttendeeIds() != null && !request.getAttendeeIds().isEmpty()) {
                request.getAttendeeIds().forEach(attendeeId ->
                        eventAttendeeRepository.linkAttendee(event.getEventId(), attendeeId));
            }
            event.setAttendees(eventAttendeeRepository.getAttendeesByEventId(event.getEventId()));
            return event;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Event name already exists.");
        }
    }

    @Override
    @Transactional
    public Event updateEvent(int id, EventRequest request) {
        try {
            Event updated = eventRepository.updateEvent(id, request);
            if (updated == null) throw new EntityNotFoundException("Event with id " + id + " not found.");
            eventAttendeeRepository.unlinkAttendees(id);
            if (request.getAttendeeIds() != null && !request.getAttendeeIds().isEmpty()) {
                request.getAttendeeIds().forEach(attendeeId ->
                        eventAttendeeRepository.linkAttendee(id, attendeeId));
            }

            updated.setAttendees(eventAttendeeRepository.getAttendeesByEventId(id));
            return updated;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Event name already exists.");
        }
    }

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return eventRepository.getAllEvents(size, offset);
    }

    @Override
    public void deleteEventById(Integer id) {
        int rowAffected = eventRepository.deleteEvent(id);
        if (rowAffected < 1) throw new EntityNotFoundException("Event with id " + id + " not found.");
    }
}