package io.roa.springexceptionhandler.service;


import io.roa.springexceptionhandler.model.entity.Venue;
import io.roa.springexceptionhandler.model.request.VenueRequest;

import java.util.List;

public interface VenueService {

    Venue createVenue(VenueRequest request);

    Venue updateVenue(int id, VenueRequest request);

    Venue getVenueById(int id);

    List<Venue> getAllVenues(Integer page, Integer size);

    void deleteVenueById(int id);
}
