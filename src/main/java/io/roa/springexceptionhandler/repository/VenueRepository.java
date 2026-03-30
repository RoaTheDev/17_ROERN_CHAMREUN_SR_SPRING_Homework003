package io.roa.springexceptionhandler.repository;

import io.roa.springexceptionhandler.model.entity.Venue;
import io.roa.springexceptionhandler.model.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
    String venueMapper = "venueMapper";

    @Results(id = venueMapper, value = {
            @Result(property = Venue.Fields.venueId, column = "venue_id"),
            @Result(property = Venue.Fields.venueName, column = "venue_name"),
            @Result(property = Venue.Fields.location, column = "location"),
    })

    @Select("""
            INSERT INTO venues VALUES (DEFAULT, #{req.venueName}, #{req.location}) RETURNING *;
            """)
    Venue saveVenue(@Param("req") VenueRequest request);

    @ResultMap(venueMapper)
    @Select("""
            SELECT * FROM venues WHERE venue_id = #{id} LIMIT 1;
            """)
    Venue getVenueById(int id);

    @ResultMap(venueMapper)
    @Select("""
            SELECT * FROM venues LIMIT #{size} OFFSET #{offset};
            """)
    List<Venue> getVenueList(int size, int offset);

    @ResultMap(venueMapper)
    @Select("""
            UPDATE venues SET venue_name = #{req.venueName}, location = #{req.location}
                          WHERE venue_id = #{id} RETURNING *;
            """)
    Venue updateVenue(int id, @Param("req") VenueRequest request);

    @Delete("""
            DELETE FROM venues WHERE  venue_id = #{id}
            """)
    int removeVenue(int id);
}

