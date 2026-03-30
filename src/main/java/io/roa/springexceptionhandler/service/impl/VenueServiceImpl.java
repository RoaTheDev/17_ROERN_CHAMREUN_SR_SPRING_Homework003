package io.roa.springexceptionhandler.service.impl;

import io.roa.springexceptionhandler.exception.EntityExistsException;
import io.roa.springexceptionhandler.exception.EntityNotFoundException;
import io.roa.springexceptionhandler.model.entity.Venue;
import io.roa.springexceptionhandler.model.request.VenueRequest;
import io.roa.springexceptionhandler.repository.VenueRepository;
import io.roa.springexceptionhandler.service.VenueService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue createVenue(VenueRequest request) {
        try {
            return venueRepository.saveVenue(request);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Venue name already exists.");
        }
    }

    @Override
    public Venue updateVenue(int id, VenueRequest request) {
        try {
            Venue updated = venueRepository.updateVenue(id, request);
            if (updated == null) {
                throw new EntityNotFoundException("Venue with id " + id + " not found.");
            }
            return updated;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Venue name already exists.");
        }
    }

    @Override
    public Venue getVenueById(int id) {
        Venue venue = venueRepository.getVenueById(id);
        if (venue == null) throw new EntityNotFoundException("Venue with id " + id + " not found.");
        return venue;
    }

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return venueRepository.getVenueList(size, offset);
    }

    @Override
    public void deleteVenueById(int id) {
        int rowAffected = venueRepository.removeVenue(id);
        if (rowAffected < 1) {
            throw new EntityNotFoundException("Venue with id " + id + " not found.");
        }
    }
}
