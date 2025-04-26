package br.edu.unipe.domain.address.dto;

import br.edu.unipe.domain.address.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressInputDTO {
    @NotBlank(message = "Street is required")
    private String street;

    private String complement;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "State is required")
    private State state;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getComplement() { return complement; }
    public void setComplement(String complement) { this.complement = complement; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
}