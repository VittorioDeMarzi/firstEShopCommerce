package de.supercode.eCommerce.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiError {

    private HttpStatus status;
    @Getter
    private String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status.value();
    }

}