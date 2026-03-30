package io.roa.springexceptionhandler.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequest {

    @NotNull(message = "Venue name cannot be null.")
    @NotBlank(message = "Venue name cannot be blank.")
    @Length(max = 100, message = "Venue name must be between 0 to 100")
    private String venueName;

    @NotNull(message = "Location name cannot be null")
    @NotBlank(message = "Location name cannot be blank")
    @Length(max = 150, message = "Venue location must be between 0 to 150")
    private String location;
}
