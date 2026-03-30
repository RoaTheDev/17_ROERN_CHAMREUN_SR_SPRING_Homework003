package io.roa.springexceptionhandler.controller;

import io.roa.springexceptionhandler.model.entity.Venue;
import io.roa.springexceptionhandler.model.request.VenueRequest;
import io.roa.springexceptionhandler.model.response.ApiResponse;
import io.roa.springexceptionhandler.service.VenueService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable Integer venueId) {
        Venue venue = venueService.getVenueById(venueId);
        return ResponseEntity.ok(ApiResponse.ok(venue, HttpStatus.OK, "Retrieved venue with id " + venue.getVenueId() + " successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenue(@RequestParam(defaultValue = "1") @Valid @Positive(message = "must be greater than 0") Integer page, @RequestParam(defaultValue = "10") @Valid @Positive(message = "must be greater than 0") Integer size) {
        List<Venue> venues = venueService.getAllVenues(page, size);
        return ResponseEntity.ok(ApiResponse.ok(venues, HttpStatus.OK, "Retrieved venues successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> createVenue(@RequestBody @Valid VenueRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(venueService.createVenue(request), HttpStatus.CREATED, "Created venue successfully"));
    }

    @PutMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(@PathVariable Integer venueId, @RequestBody @Valid VenueRequest request) {
        Venue venue = venueService.updateVenue(venueId, request);
        return ResponseEntity.ok(ApiResponse.ok(venue, HttpStatus.OK, "Updated venue with id " + venueId + " successfully"));
    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Integer venueId) {
        venueService.deleteVenueById(venueId);
        return ResponseEntity.ok(ApiResponse.ok(HttpStatus.OK, "Deleted venue with id " + venueId + " successfully"));
    }
}
