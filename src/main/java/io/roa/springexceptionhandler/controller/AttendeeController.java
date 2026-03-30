package io.roa.springexceptionhandler.controller;

import io.roa.springexceptionhandler.model.entity.Attendee;
import io.roa.springexceptionhandler.model.request.AttendeeRequest;
import io.roa.springexceptionhandler.model.request.AttendeeUpdateRequest;
import io.roa.springexceptionhandler.model.response.ApiResponse;
import io.roa.springexceptionhandler.service.AttendeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable Integer attendeeId) {
        Attendee attendee;
        attendee = attendeeService.getAttendeeById(attendeeId);
        return ResponseEntity.ok(ApiResponse.ok(attendee, HttpStatus.OK, "Retrieved attendee with id " + attendee.getAttendeeId() + " successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendee(@RequestParam(defaultValue = "1") @Valid @Positive(message = "must be greater than 0") Integer page, @RequestParam(defaultValue = "10") @Valid @Positive(message = "must be greater than 0") Integer size) {
        List<Attendee> attendees = attendeeService.getAllAttendee(page, size);
        return ResponseEntity.ok(ApiResponse.ok(attendees, HttpStatus.OK, "Retrieved attendees successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(@RequestBody @Valid AttendeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(attendeeService.createAttendee(request), HttpStatus.CREATED, "Created attendee successfully"));
    }

    @PutMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendee(@PathVariable Integer attendeeId, @RequestBody @Valid AttendeeUpdateRequest request) {
        Attendee attendee = attendeeService.updateAttendee(attendeeId, request);
        return ResponseEntity.ok(ApiResponse.ok(attendee, HttpStatus.OK, "Updated attendee with id " + attendeeId + " successfully"));
    }

    @DeleteMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Void>> deleteAttendeeById(@PathVariable Integer attendeeId) {
        attendeeService.deleteAttendeeById(attendeeId);
        return ResponseEntity.ok(ApiResponse.ok(HttpStatus.OK, "Deleted attendee with id " + attendeeId + " successfully"));
    }
}
