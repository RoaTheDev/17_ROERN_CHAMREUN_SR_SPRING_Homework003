package io.roa.springexceptionhandler.service;

import io.roa.springexceptionhandler.model.entity.Event;
import io.roa.springexceptionhandler.model.request.EventRequest;

import java.util.List;

public interface EventService {

    Event getEventById(int id);

    Event createEvent(EventRequest request);

    Event updateEvent(int id, EventRequest request);

    List<Event> getAllEvents(Integer page, Integer size);

    void deleteEventById(Integer id);

}
