package io.roa.springexceptionhandler.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeRequest {

    @NotNull(message = "Attendee name cannot be null.")
    @NotBlank(message = "Attendee name cannot be blank.")
    @Pattern(message = "Attendee name can only contain character", regexp = "^[a-zA-Z\\s]+$")
    @Length(min = 1, max = 50, message = "Attendee name must be between 1 to 50")
    private String attendeeName;

    @NotNull(message = "Email cannot be null.")
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email must be a valid format (e.g., abc@gmail.com)")
    @Length(max = 50, message = "Email must be between 1 to 50")
    private String email;

}
