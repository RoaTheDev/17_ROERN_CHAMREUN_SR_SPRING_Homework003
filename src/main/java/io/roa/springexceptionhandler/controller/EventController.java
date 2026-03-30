package io.roa.springexceptionhandler.controller;

import io.roa.springexceptionhandler.model.entity.Event;
import io.roa.springexceptionhandler.model.request.EventRequest;
import io.roa.springexceptionhandler.model.response.ApiResponse;
import io.roa.springexceptionhandler.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable Integer eventId) {
        Event event = eventService.getEventById(eventId);
        return ResponseEntity.ok(
                ApiResponse.ok(event, HttpStatus.OK,
                        "Retrieved event with id " + event.getEventId() + " successfully")
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(
            @RequestParam(defaultValue = "1") @Valid @Positive(message = "must be greater than 0") Integer page,
            @RequestParam(defaultValue = "10") @Valid @Positive(message = "must be greater than 0") Integer size
    ) {
        List<Event> events = eventService.getAllEvents(page, size);
        return ResponseEntity.ok(
                ApiResponse.ok(events, HttpStatus.OK, "Retrieved events successfully")
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@RequestBody @Valid EventRequest request) {
        Event event = eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.ok(event, HttpStatus.CREATED, "Created event successfully")
        );
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> updateEvent(
            @PathVariable Integer eventId,
            @RequestBody @Valid EventRequest request
    ) {
        Event event = eventService.updateEvent(eventId, request);
        return ResponseEntity.ok(
                ApiResponse.ok(event, HttpStatus.OK,
                        "Updated event with id " + eventId + " successfully")
        );
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Void>> deleteEventById(@PathVariable Integer eventId) {
        eventService.deleteEventById(eventId);
        return ResponseEntity.ok(
                ApiResponse.ok(HttpStatus.OK,
                        "Deleted event with id " + eventId + " successfully")
        );
    }
}