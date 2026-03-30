package io.roa.springexceptionhandler.service;

import io.roa.springexceptionhandler.model.entity.Attendee;
import io.roa.springexceptionhandler.model.request.AttendeeRequest;
import io.roa.springexceptionhandler.model.request.AttendeeUpdateRequest;

import java.util.List;

public interface AttendeeService {

    Attendee getAttendeeById(int id);

    Attendee createAttendee(AttendeeRequest request);

    Attendee updateAttendee(int id, AttendeeUpdateRequest request);

    List<Attendee> getAllAttendee(Integer page, Integer size);

    void deleteAttendeeById(Integer id);
}
