package io.roa.springexceptionhandler.repository;

import io.roa.springexceptionhandler.model.entity.Attendee;
import io.roa.springexceptionhandler.model.request.AttendeeRequest;
import io.roa.springexceptionhandler.model.request.AttendeeUpdateRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    String attendeeMapper = "attendeeMapper";

    @Results(id = attendeeMapper, value = {
            @Result(property = Attendee.Fields.attendeeId, column = "attendee_id"),
            @Result(property = Attendee.Fields.attendeeName, column = "attendee_name"),
            @Result(property = Attendee.Fields.email, column = "email"),
    })

    @Select("""
            INSERT INTO attendees VALUES (DEFAULT,#{req.attendeeName},#{req.email}) RETURNING *;
            """)
    Attendee saveAttendee(@Param("req") AttendeeRequest request);

    @ResultMap(attendeeMapper)
    @Select("""
            SELECT * FROM  attendees WHERE attendee_id = #{id} LIMIT 1;
            """)
    Attendee getAttendeeById(int id);

    @ResultMap(attendeeMapper)
    @Select("""
            SELECT * FROM attendees LIMIT #{size} OFFSET #{offset}
            """)
    List<Attendee> getAllAttendee(int size, int offset);


    @ResultMap(attendeeMapper)
    @Select("""
            UPDATE attendees SET attendee_name = #{req.attendeeName}
            WHERE attendee_id = #{id}
            RETURNING *;
            """)
    Attendee updateAttendee(int id, @Param("req") AttendeeUpdateRequest request);

    @Delete("""
            DELETE FROM attendees WHERE attendee_id = #{id}
            """)
    int deleteAttendee(int id);
}
