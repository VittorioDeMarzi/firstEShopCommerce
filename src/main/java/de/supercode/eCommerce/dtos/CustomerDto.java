package de.supercode.eCommerce.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email(message = "Email is not valid", regexp = ".+@.+\\..+")
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @NotBlank
    private String street;
    @NotBlank
    private String houseNumber;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String country;
}
