package io.roa.springexceptionhandler.repository;

import io.roa.springexceptionhandler.model.entity.Event;
import io.roa.springexceptionhandler.model.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {
    String eventMapper = "eventMapper";

    @Results(id = eventMapper, value = {
            @Result(property = Event.Fields.eventId, column = "event_id"),
            @Result(property = Event.Fields.eventName, column = "event_name"),
            @Result(property = Event.Fields.eventDate, column = "event_date"),
            @Result(property = "venue.venueId", column = "venue_id"),
            @Result(property = "venue.venueName", column = "venue_name"),
            @Result(property = "venue.location", column = "location"),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "io.roa.springexceptionhandler.repository.EventAttendeeRepository.getAttendeesByEventId"))
    })
    @Select("""
            SELECT e.event_id, e.event_name, e.event_date,
                   v.venue_id, v.venue_name, v.location
            FROM events e
            JOIN venues v ON e.venue_id = v.venue_id
            WHERE e.event_id = #{id};
            """)
    Event getEventById(int id);

    @ResultMap(eventMapper)
    @Select("""
            SELECT e.event_id, e.event_name, e.event_date,
                   v.venue_id, v.venue_name, v.location
            FROM events e JOIN venues v ON e.venue_id = v.venue_id
            LIMIT #{limit} OFFSET #{offset};
            """)
    List<Event> getAllEvents(int limit, int offset);

    @ResultMap(eventMapper)
    @Select("""
            INSERT INTO events VALUES (DEFAULT, #{req.eventName}, #{req.eventDate}, #{req.venueId})
            RETURNING event_id, event_name, event_date, venue_id;
            """)
    Event saveEvent(@Param("req") EventRequest request);

    @ResultMap(eventMapper)
    @Select("""
            UPDATE events
            SET event_name = #{req.eventName}, event_date = #{req.eventDate}, venue_id = #{req.venueId}
            WHERE event_id = #{id}
            RETURNING event_id, event_name, event_date, venue_id;
            """)
    Event updateEvent(int id, @Param("req") EventRequest request);

    @Delete("""
            DELETE FROM events WHERE event_id = #{id}
            """)
    int deleteEvent(int id);
}