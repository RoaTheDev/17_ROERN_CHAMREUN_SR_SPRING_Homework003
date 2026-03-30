package io.roa.springexceptionhandler.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private T payload;
    private Instant timestamp;
    private String status;
    private String message;

    public ApiResponse(T payload, HttpStatus status, String message) {
        this.payload = payload;
        this.status = status.getReasonPhrase();
        this.message = message;
        this.timestamp = Instant.now();
    }

    public static <T> ApiResponse<T> ok(T payload, HttpStatus status, String message) {
        return new ApiResponse<>(payload, status, message);
    }

    public static <T> ApiResponse<T> ok(HttpStatus status, String message) {
        return new ApiResponse<>(null, status, message);
    }

    public static <T> ApiResponse<T> err(HttpStatus status, String message) {
        return new ApiResponse<>(null, status, message);
    }
}

