package io.roa.springexceptionhandler.service.impl;

import io.roa.springexceptionhandler.exception.EntityExistsException;
import io.roa.springexceptionhandler.exception.EntityNotFoundException;
import io.roa.springexceptionhandler.model.entity.Attendee;
import io.roa.springexceptionhandler.model.request.AttendeeRequest;
import io.roa.springexceptionhandler.model.request.AttendeeUpdateRequest;
import io.roa.springexceptionhandler.repository.AttendeeRepository;
import io.roa.springexceptionhandler.service.AttendeeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public Attendee getAttendeeById(int id) {
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (attendee == null) {
            throw new EntityNotFoundException("Attendee with id " + id + " not found.");
        }
        return attendee;
    }

    @Override
    public Attendee createAttendee(AttendeeRequest request) {
        try {
            return attendeeRepository.saveAttendee(request);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Email already exists.");
        }
    }

    @Override
    public Attendee updateAttendee(int id, AttendeeUpdateRequest request) {
        Attendee updated = attendeeRepository.updateAttendee(id, request);
        if (updated == null) {
            throw new EntityNotFoundException("Attendee with id " + id + " not found.");
        }
        return updated;
    }

    @Override
    public List<Attendee> getAllAttendee(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return attendeeRepository.getAllAttendee(size, offset);
    }

    @Override
    public void deleteAttendeeById(Integer id) {
        int rowAffected = attendeeRepository.deleteAttendee(id);
        if (rowAffected < 1) {
            throw new EntityNotFoundException("Attendee with id " + id + " not found.");
        }
    }
}
