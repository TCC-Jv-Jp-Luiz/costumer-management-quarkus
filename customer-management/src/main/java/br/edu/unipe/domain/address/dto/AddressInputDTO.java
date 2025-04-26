package br.edu.unipe.domain.address.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressInputDTO {
    @NotBlank(message = "Street is required")
    public String street;
    public String complement;
    @NotBlank(message = "City is required")
    public String city;

    @NotBlank(message = "State is required")
    public String state;

    @NotBlank(message = "Postal code is required")
    public String postalCode;
}