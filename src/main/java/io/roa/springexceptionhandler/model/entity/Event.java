package io.roa.springexceptionhandler.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Event {
    private Integer eventId;
    private String eventName;
    private Date eventDate;
    private Venue venue;
    private List<Attendee> attendees;
}
