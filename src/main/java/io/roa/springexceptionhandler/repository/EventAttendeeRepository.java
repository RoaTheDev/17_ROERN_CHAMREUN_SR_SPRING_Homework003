package io.roa.springexceptionhandler.repository;

import io.roa.springexceptionhandler.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {

    @Insert("""
            INSERT INTO event_attendee (event_id, attendee_id) VALUES (#{eventId}, #{attendeeId})
            """)
    void linkAttendee(@Param("eventId") int eventId, @Param("attendeeId") int attendeeId);

    @Delete("""
            DELETE FROM event_attendee WHERE event_id = #{eventId}
            """)
    void unlinkAttendees(int eventId);

    @Results({
            @Result(property = Attendee.Fields.attendeeId, column = "attendee_id"),
            @Result(property = Attendee.Fields.attendeeName, column = "attendee_name"),
            @Result(property = Attendee.Fields.email, column = "email"),
    })
    @Select("""
            SELECT a.attendee_id, a.attendee_name, a.email
            FROM attendees a
            JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
            WHERE ea.event_id = #{eventId};
            """)
    List<Attendee> getAttendeesByEventId(int eventId);
}
