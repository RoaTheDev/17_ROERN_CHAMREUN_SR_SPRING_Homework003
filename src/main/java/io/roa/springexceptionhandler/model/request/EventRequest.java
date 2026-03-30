package io.roa.springexceptionhandler.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @NotBlank(message = "Event name must not be blank")
    @NotNull(message = "Event name must not be null")
    private String eventName;

    @NotNull(message = "Event date must not be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Event date must be in the future")
    private Date eventDate;
    private Integer venueId;
    private List<Integer> attendeeIds;
}
